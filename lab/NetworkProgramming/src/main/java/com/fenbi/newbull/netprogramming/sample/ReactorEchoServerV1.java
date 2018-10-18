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
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

/**
 *
 * 单线程 Reactor.  及改进版的 在线程池里 Process
 *
 * @author xuhongfeng
 */
public class ReactorEchoServerV1 implements EchoServer {

    private Reactor reactor;

    @Override
    public void start(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("0.0.0.0", port));
        serverSocketChannel.configureBlocking(false);

        reactor = new Reactor();
        reactor.register(serverSocketChannel, SelectionKey.OP_ACCEPT, new Acceptor());
        reactor.start();

    }

    private class Reactor extends Thread {

        private final Selector selector;

        public Reactor() {
            try {
                selector = SelectorProvider.provider().openSelector();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void register(SelectableChannel channel, int ops, Handler handler) {
            try {
                channel.register(selector, ops, handler);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

            try {
                while (!Thread.currentThread().isInterrupted()) {
                    selector.select();
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

        @Override
        public void handle(SelectionKey key) {

            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            Selector selector = key.selector();

            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
//                System.out.println("Got new client:" + socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ, new Decoder());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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
                    if (!sizeBuf.hasRemaining()) {

                        // consume sizeBuf
                        sizeBuf.flip();
                        size = sizeBuf.getInt();

                        dataBuf = ByteBuffer.allocate(size);
                        readingSize = false;
                    }
                } else {
                    channel.read(dataBuf);
                    if (!dataBuf.hasRemaining()) {

                        dataBuf.flip();
                        String msg = new String(dataBuf.array());

                        new Processor(key.selector(), channel, msg).run();
                        /**
                         *
                         * 这个地方可以优化为在线程池里执行业务操作:
                         *
                         *  threadPool.execute(new Processor(key.selector(), channel, msg));
                         *
                         */
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