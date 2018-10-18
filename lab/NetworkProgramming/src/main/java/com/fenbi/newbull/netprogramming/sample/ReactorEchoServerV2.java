/**
 * @(#)ReactorEchoServer.java, Aug 30, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.netprogramming.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 *
 * 单线程 Reactor.  及改进版的 在线程池里 Process
 *
 * @author xuhongfeng
 */
public class ReactorEchoServerV2 implements EchoServer {

    private Reactor acceptReactor;
    private Reactor[] subReactors;

    @Override
    public void start(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("0.0.0.0", port));
        serverSocketChannel.configureBlocking(false);

        acceptReactor = new Reactor();
        acceptReactor.start();

        subReactors = new Reactor[5];
        for (int i = 0; i < subReactors.length; i++) {
            subReactors[i] = new Reactor();
            subReactors[i].start();
        }

        Acceptor acceptor = new Acceptor(subReactors);
        acceptReactor.register(serverSocketChannel, SelectionKey.OP_ACCEPT, acceptor);

    }

    private class Reactor extends Thread {

        private final Selector selector;
        private final Queue<FutureTask<SelectionKey>> registerQueues = new LinkedList<>();

        public Reactor() {
            try {
                selector = SelectorProvider.provider().openSelector();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public SelectionKey register(SelectableChannel channel, int ops, Handler handler) {
            Callable<SelectionKey> callable = () -> channel.register(selector, ops, handler);
            if (Thread.currentThread() == this) {
                try {
                    return callable.call();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                /**
                 *  Selector.select() 会持有一个锁并 blocking wait.
                 *  Selector.register() 需要获取上面提到的锁.
                 *  所以如果 select() register() 不是一个线程， 会死锁.
                 *
                 *  这段逻辑是唤醒 Reactor 的线程(即select的线程) 来做 register, 避免死锁.
                 */
                FutureTask<SelectionKey> task = new FutureTask<>(callable);
                synchronized (registerQueues) {
                    registerQueues.add(task);
                    selector.wakeup();
                }
                try {
                    return task.get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public void run() {

            try {
                while (!Thread.currentThread().isInterrupted()) {
//                    System.out.println("selecting " + Thread.currentThread());
                    selector.select();
                    /**
                     * @see {@link #register(SelectableChannel, int, Handler)}
                     */
                    synchronized (registerQueues) {
                        while (!registerQueues.isEmpty()) {
                            registerQueues.poll().run();
                        }
                    }
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        Handler handler = (Handler) key.attachment();
//                        System.out.println("key=" + key.interestOps() + ", handler=" + handler.getClass().getSimpleName());
                        handler.handle(key);
                        iter.remove();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private interface Handler {

        void handle(SelectionKey key);
    }

    private class Acceptor implements Handler {

        private final Reactor[] subReactors;
        private int nextIndex = 0;

        private Acceptor(Reactor[] subReactors) {
            this.subReactors = subReactors;
        }

        @Override
        public void handle(SelectionKey key) {

            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            Reactor subReactor = nextSubReactor();

            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
//                System.out.println("Got new client:" + socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                subReactor.register(socketChannel, SelectionKey.OP_READ, new Decoder());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Reactor nextSubReactor() {
            int next = (nextIndex + 1) % subReactors.length;
            nextIndex = next;
            return subReactors[next];
        }
    }

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private class Decoder implements Handler {

        private boolean readingSize = true;

        private ByteBuffer sizeBuf = ByteBuffer.allocate(4);
        private int size;

        private ByteBuffer dataBuf;

        @Override
        public void handle(SelectionKey key) {
            SocketChannel channel = (SocketChannel) key.channel();
            try {
//                System.out.println("decode. readingSize:" + readingSize);
                if (readingSize) {
                    channel.read(sizeBuf);
                    if (sizeBuf.position() == 4) {
                        sizeBuf.flip();
                        size = sizeBuf.getInt();
                        dataBuf = ByteBuffer.allocate(size);
                        readingSize = false;
                    }
                } else {
                    channel.read(dataBuf);
                    if (dataBuf.position() == size) {

                        dataBuf.flip();
                        String msg = new String(dataBuf.array());

                        threadPool.execute(new Processor(key.selector(), channel, msg));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private class Processor implements Runnable {

        private final Selector selector;
        private final SocketChannel channel;
        private final String msg;

        public Processor(Selector selector, SocketChannel channel, String msg) {
            this.selector = selector;
            this.channel = channel;
            this.msg = msg;
        }

        @Override
        public void run() {
            try {
                channel.register(selector, SelectionKey.OP_WRITE, new Encoder(msg));
                selector.wakeup();
//                System.out.println("Process");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class Encoder implements Handler {

        private final ByteBuffer sizeBuf;
        private final ByteBuffer dataBuf;

        private boolean writingSize = true;

        public Encoder(String msg) {
            int size = msg.length();
            sizeBuf = ByteBuffer.allocate(4);
            sizeBuf.putInt(size);
            sizeBuf.flip();

            dataBuf = ByteBuffer.allocate(size);
            dataBuf.put(msg.getBytes());
            dataBuf.flip();
        }

        @Override
        public void handle(SelectionKey key) {
//            System.out.println("encoder. writingSize:" + writingSize);
            SocketChannel channel = (SocketChannel) key.channel();
            try {
                if (writingSize) {
                    channel.write(sizeBuf);
                    if (!sizeBuf.hasRemaining()) {
                        writingSize = false;
                    }
                } else {
                    channel.write(dataBuf);
                    if (!dataBuf.hasRemaining()) {
                        channel.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}