/**
 * @(#)EchoClient.java, Aug 27, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.netprogramming.sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author xuhongfeng
 */
public class EchoClient {

    public static String sendAndReceive(int port, String msg) throws Exception {

        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", port));
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            int size = msg.getBytes().length;
            output.writeInt(size);
            output.write(msg.getBytes());

            size = input.readInt();
            byte[] buf = new byte[size];
            int read = 0;
            while (read < size) {
                read += input.read(buf, read, size - read);
            }
            output.write(buf);

            return new String(buf);

        } finally {
            output.close();
            input.close();
            socket.close();
        }

    }
}