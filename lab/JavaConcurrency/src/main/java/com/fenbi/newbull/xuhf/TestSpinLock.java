/**
 * @(#)TestSpinLock.java, Jul 21, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.xuhf;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xuhongfeng
 */
public class TestSpinLock {

    private static class SpinLock {

        private final AtomicLong owner = new AtomicLong();

        public void lock() {
            long threadId = Thread.currentThread().getId();
            while (!owner.compareAndSet(0, threadId)) {
            }
        }

        public void unlock() {
            owner.set(0);
        }
    }
}