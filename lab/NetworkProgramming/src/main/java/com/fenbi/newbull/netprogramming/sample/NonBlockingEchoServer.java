/**
 * @(#)NonBlockingEchoServer.java, Aug 27, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.netprogramming.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xuhongfeng
 */
public class NonBlockingEchoServer implements EchoServer {

    private ServerSocketChannel serverSocketChannel;
    private ServerThread serverThread;

    private final Set<Client> clients = new HashSet<>();

    @Override
    public void start(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("0.0.0.0", port));
        serverSocketChannel.configureBlocking(false);

        serverThread = new ServerThread();
        serverThread.start();
    }

    /**
     * 维护每个socket 连接的状态.
     */
    private static class Client {

        private final SocketChannel socketChannel;

        private State state = State.ReadingSize; // 状态机的定义
        private ByteBuffer sizeBuf = ByteBuffer.allocate(4);
        private int size;
        private ByteBuffer dataBuf;

        public Client(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        public void close() {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void loop() {
            try {
                if (state == Client.State.ReadingSize) {
                    socketChannel.read(sizeBuf);
                    if (!sizeBuf.hasRemaining()) {

                        // consume sizeBuf
                        sizeBuf.flip();
                        size = sizeBuf.getInt();

                        dataBuf = ByteBuffer.allocate(size);
                        state = Client.State.ReadingData;
                    }
                } else if (state == Client.State.ReadingData) {
                    socketChannel.read(dataBuf);
                    if (!dataBuf.hasRemaining()) {
                        dataBuf.flip();

                        sizeBuf.clear();
                        sizeBuf.putInt(size);
                        sizeBuf.flip();

                        state = State.WritingSize;
                    }
                } else if (state == State.WritingSize) {
                    socketChannel.write(sizeBuf);
                    if (!sizeBuf.hasRemaining()) {
                        state = State.WritingData;
                    }
                } else if (state == State.WritingData) {
                    socketChannel.write(dataBuf);
                    if (!dataBuf.hasRemaining()) {
                        state = State.Finished;
                    }
                } else {
                    throw new IllegalStateException();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        enum State {
            ReadingSize, ReadingData, WritingSize, WritingData, Finished
        }
    }

    private class ServerThread extends Thread {

        @Override
        public void run() {
            while (!Thread.interrupted()) {

                acceptNewClients();

                Iterator<Client> iter = clients.iterator();
                while (iter.hasNext()) {
                    Client client = iter.next();
                    client.loop();
                    if (client.state == Client.State.Finished) {
                        client.close();
                        iter.remove();
                    }
                }

                System.out.println("clients.size=" + clients.size());
                if (clients.size() == 0) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void acceptNewClients() {
            while (!Thread.interrupted()) {
                try {
                    SocketChannel socketChannel = serverSocketChannel.accept(); // non-blocking
                    if (socketChannel != null) {
                        socketChannel.configureBlocking(false);
                        System.out.println("Got newClient : " + socketChannel.getRemoteAddress());
                        Client newClient = new Client(socketChannel);
                        clients.add(newClient);
                    } else {
                        System.out.println("no newClient");
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}