/**
 * @(#)MultiThreadEchoServer.java, Aug 30, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.netprogramming.sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xuhongfeng
 */
public class MultiThreadEchoServer implements EchoServer {

    private ServerSocket serverSocket;
    private ServerThread serverThread;

    @Override
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("0.0.0.0", port));

        serverThread = new ServerThread();
        serverThread.start();
    }

    private class ServerThread extends Thread {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    final Socket socket =  serverSocket.accept();
                    Runnable runnable = () -> {
                        DataInputStream input = null;
                        DataOutputStream output = null;
                        try {
                            input = new DataInputStream(socket.getInputStream());
                            output = new DataOutputStream(socket.getOutputStream());

                            int size = input.readInt();
                            byte[] buf = new byte[size];
                            int read = 0;
                            while (read < size) {
                                read += input.read(buf, read, size - read);
                            }
                            output.writeInt(size);
                            output.write(buf);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                input.close();
                                output.close();
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    new Thread(runnable).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}