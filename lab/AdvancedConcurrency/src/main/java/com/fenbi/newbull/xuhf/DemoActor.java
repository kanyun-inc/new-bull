/**
 * @(#)DemoActor.java, Jul 25, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.xuhf;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author xuhongfeng
 */
public class DemoActor {

    class AccountActor {

        private final BlockingQueue<Message> mailbox = new LinkedBlockingDeque<>();

        // 状态
        private final Map<Integer, Account> accounts = new HashMap<>();

        public void send(Message msg) {
            mailbox.add(msg);
        }

        private class WorkThread extends Thread {
            @Override
            public void run() {
                try {

                    Message msg = mailbox.take();
                    if (msg instanceof TransferMessage) {
                        TransferMessage transfer = (TransferMessage) msg;
                        accounts.get(transfer.fromUserId).money -= transfer.money;
                        accounts.get(transfer.toUserId).money += transfer.money;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class Message {
    }

    class TransferMessage extends Message {
        private int fromUserId;
        private int toUserId;
        private int money;
    }


    class Account {
        private int money;
    }
}