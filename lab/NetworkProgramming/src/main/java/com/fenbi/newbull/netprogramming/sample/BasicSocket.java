/**
 * @(#)BasicSocket.java, Sep 02, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.netprogramming.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xuhongfeng
 */
public class BasicSocket {

    public void baseSocketTest() throws IOException {

        int TCP_BACKLOG = 1024;
        ServerSocket serverSocket = new ServerSocket(8888, TCP_BACKLOG);

        // SocketOptions.SO_REUSEADDR
        serverSocket.setReuseAddress(true);
        // SocketOptions.SO_REUSEPORT,  since JDK9

        Socket clientSocket = new Socket();
        // SocketOptions.SO_TIMEOUT,  connect Timeout
        clientSocket.connect(new InetSocketAddress("127.0.0.1", 8888), 3);
        clientSocket.setSoTimeout(3);

        //SocketOptions.SO_KEEPALIVE
        clientSocket.setKeepAlive(true);

        //SocketOptions.SO_LINGER
        clientSocket.setSoLinger(true, 3);

        //SocketOptions.TCP_NODELAY
        clientSocket.setTcpNoDelay(true);
    }
}