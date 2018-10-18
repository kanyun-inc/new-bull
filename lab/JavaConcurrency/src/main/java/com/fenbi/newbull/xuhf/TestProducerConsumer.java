/**
 * @(#)TestProducerConsumer.java, Jul 21, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.xuhf;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuhongfeng
 */
public class TestProducerConsumer {


    private static class SynchronizedImpl {

        private final Queue<Object> queue = new LinkedList<Object>();
        private final int capacity;

        public SynchronizedImpl(int capacity) {
            this.capacity = capacity;
        }

        public void produce(Object obj) throws InterruptedException {
            synchronized (queue) {
                while (queue.size() == capacity) {
                    queue.wait();
                }
                queue.add(obj);
                queue.notifyAll();
            }
        }

        public Object consume() throws InterruptedException {
            Object ret = null;
            synchronized (queue) {
                while (queue.size() == 0) {
                    queue.wait();
                }
                ret = queue.poll();
                queue.notifyAll();
            }
            return ret;
        }
    }



    private static class LockImpl {

        private final Queue<Object> queue = new LinkedList<Object>();
        private final int capacity;

        private final Lock lock = new ReentrantLock();
        private final Condition notEmpty = lock.newCondition();
        private final Condition notFull = lock.newCondition();

        public LockImpl(int capacity) {
            this.capacity = capacity;
        }

        public void produce(Object obj) throws InterruptedException {
            lock.lock();
            try {
                while (queue.size() == capacity) {
                    notFull.await();
                }
                queue.add(obj);
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
            synchronized (queue) {
                while (queue.size() == capacity) {
                    queue.wait();
                }
            }
        }

        public Object consume() throws InterruptedException {
            lock.lock();
            try {
                while (queue.size() == 0) {
                    notEmpty.await();
                }
                Object ret = queue.poll();
                notFull.signalAll();
                return ret;
            } finally {
                lock.unlock();
            }
        }
    }
}