/**
 * @(#)TestSync.java, Jul 21, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.xuhf;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuhongfeng
 */
public class TestSync {


    public static void main(String[] args) throws InterruptedException {
//        testSimpleFuture();
        testWaitWithoutMonitor();
    }

    private static void testSimpleFuture() throws InterruptedException {

        final SimpleFuture future = new SimpleFuture();
//        final SimpleFutureV2 future = new SimpleFutureV2();

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.set("hello");
        });
        t.setDaemon(true);
        t.start();

        long startTime = System.currentTimeMillis();
        String value = future.get();
        System.out.print("value = " + value + ", cost=" + (System.currentTimeMillis() - startTime));
    }

    private static class SimpleFuture {

        private String object;

        public String get() throws InterruptedException {
            synchronized (this) {
                while (object == null) {
                    this.wait();
                }
                return object;
            }
        }

        public void set(String value) {
            synchronized (this) {
                object = value;
                this.notifyAll();
            }
        }
    }


    private static class SimpleFutureV2 {

        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();

        private String object;

        public String get() throws InterruptedException {
            lock.lock();
            try {
                while (object == null) {
                    condition.await();
                }
                return object;
            } finally {
                lock.unlock();
            }
        }

        public void set(String value) {
            lock.lock();
            try {
                object = value;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    private static void testWaitWithoutMonitor() throws InterruptedException {
        Object obj = new Object();
        obj.wait();
    }

    private static void testNotifyWithoutMonitor() throws InterruptedException {
        Object obj = new Object();
        obj.notify();
    }


}