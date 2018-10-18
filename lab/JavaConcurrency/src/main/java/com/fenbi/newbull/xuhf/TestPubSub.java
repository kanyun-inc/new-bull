/**
 * @(#)TestPubSub.java, Jul 21, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.xuhf;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * @author xuhongfeng
 */
public class TestPubSub {


    private static class PubSub {

        private final CopyOnWriteArrayList<Consumer<String>> listeners = new CopyOnWriteArrayList<>();

        public void publish(String msg) {
            listeners.forEach(l -> l.accept(msg));
        }

        public void subscribe(Consumer<String> listener) {
            listeners.add(listener);
        }
    }
}