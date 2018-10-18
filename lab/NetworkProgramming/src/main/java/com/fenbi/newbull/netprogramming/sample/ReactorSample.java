/**
 * @(#)ReactorSample.java, Sep 02, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.netprogramming.sample;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * @author xuhongfeng
 */
public class ReactorSample {

    private Reactor reactor;

    class Reactor extends Thread {

        private Selector selector;

        @Override
        public void run() {

            try {

                selector = SelectorProvider.provider().openSelector();
                while (!Thread.interrupted()) {
                    selector.select(); // block
                    Set<SelectionKey> keys = selector.selectedKeys();
                    for (SelectionKey key : keys) {
                        Handler handler = (Handler) key.attachment();
                        handler.handle(key);
                    }
                    keys.clear();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void register(SelectableChannel channel, int op, Handler handler) throws Exception {
            channel.register(selector, op, handler);
        }
    }

    // 可以有状态
    private interface Handler {

        // 在handler的过程可能会调用 reactor.register(xxx)
        void handle(SelectionKey key);
    }
}