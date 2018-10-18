/**
 * @(#)IOMultiplexEchoServer.java, Aug 30, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.netprogramming.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xuhongfeng
 */
public class IOMultiplexEchoServer implements EchoServer {

    private ServerSocketChannel serverSocketChannel;
    private ServerThread serverThread;
    private Selector selector;

    @Override
    public void start(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("0.0.0.0", port));
        serverSocketChannel.configureBlocking(false);

        selector = SelectorProvider.provider().openSelector();

        serverThread = new ServerThread();
        serverThread.start();
    }

    private void onAccept(SelectionKey key) throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        System.out.println("Got newClient : " + socketChannel.getRemoteAddress());
        Client newClient = new Client(socketChannel);
        socketChannel.register(selector, SelectionKey.OP_READ, newClient);
    }

    private void onReadable(SelectionKey key) throws IOException {
        Client client = (Client) key.attachment();
        client.onRead();

        if (client.state == Client.State.WritingSize) {
            key.interestOps(SelectionKey.OP_WRITE);
        }
    }

    private void onWritable(SelectionKey key) throws IOException {
        Client client = (Client) key.attachment();
        client.onWrite();
        if (client.state == Client.State.Finished) {
            client.close();
        }
    }

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

        private void onRead() {
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
                } else {
                    throw new IllegalStateException();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void onWrite() {
            try {
                if (state == State.WritingSize) {
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
            try {
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                while (!Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        if (key.isAcceptable()) {
                            onAccept(key);
                        } else if (key.isReadable()) {
                            onReadable(key);
                        } else if (key.isWritable()) {
                            onWritable(key);
                        }
                        iter.remove();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}