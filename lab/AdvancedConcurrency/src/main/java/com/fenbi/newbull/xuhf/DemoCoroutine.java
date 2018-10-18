/**
 * @(#)DemoCoroutine.java, Jul 25, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.xuhf;

import java.util.Collection;
import java.util.Set;

/**
 * @author xuhongfeng
 */
public class DemoCoroutine {

    public static void test() {

        Set<EchoClient> clients = buildClients();
        while (Selector.select(clients)) {
            for (EchoClient client : clients) {
                client.loop();
                if (client.status == Status.FINISHED) {
                    clients.remove(client);
                }
            }
        }
    }


    static class EchoClient {

        private Socket socket;

        private byte[] buf;
        private int total;

        private Status status;
        private int readCounter;
        private int writeCounter;


        public void start() {
            socket = accept();

            status = Status.READING;
            total = 10;
            buf = new byte[total];
            readCounter = 0;
            writeCounter = 0;
        }

        public boolean loop() {
            if (status == Status.READING) { // 从 socket 里读 total 个byte
                int read = socket.read(buf, readCounter); // 非阻塞
                readCounter += read;
                if (readCounter == total) {
                    status = Status.WRITING;
                }
            } else if (status == Status.WRITING) { // 讲 buf 写入 socket
                int write = socket.write(buf, writeCounter); // 非阻塞
                writeCounter += write;
                if (writeCounter == total) {
                    status = Status.FINISHED;
                }
            }
            return status != Status.FINISHED;
        }

        private Socket accept() {
            return null;
        }



        class Socket {

            int read(byte[] buf, int offset) {
                return -1;
            }

            int write(byte[] buf, int offset) {
                return -1;
            }
        }

    }

    enum Status {
        READING, WRITING, FINISHED
    }


    private static Set<EchoClient> buildClients() {
        return null;
    }

    static class Selector {

        public static boolean select(Collection<EchoClient> clients) {
            return true;
        }
    }
}