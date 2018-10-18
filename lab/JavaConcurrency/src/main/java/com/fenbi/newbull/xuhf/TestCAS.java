/**
 * @(#)TestCAS.java, Jul 21, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.xuhf;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xuhongfeng
 */
public class TestCAS {

    public static void main(String[] args) throws InterruptedException {
//        testNonAtomic();
        testAtomic();
    }

    private static class NonAtomicInteger {

        private int value = 0;

        public int get() {
            return value;
        }

        public void incr() {
            value++;
        }
    }

    private static void testNonAtomic() throws InterruptedException {

        final NonAtomicInteger counter = new NonAtomicInteger();
        final int threadCount = 10;
        final int countPerThread = 100000;

        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            threadPool.submit(() -> {
                for (int j = 0; j < countPerThread; j++) {
                    counter.incr();
                }
            });
        }
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
        threadPool.shutdown();

        System.out.println("expected : " + (threadCount * countPerThread));
        System.out.println("actual : " + counter.get());

    }


    private static Unsafe unsafe;
    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static long valueOffset;
    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (AtomicInt.class.getDeclaredField("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class AtomicInt {

        private int value;

        public int get() {
            return unsafe.getIntVolatile(this, valueOffset);
        }

        public void incr() {
            while (true) {
                int origin = get();
                int newValue = origin + 1;
                if (unsafe.compareAndSwapInt(this, valueOffset, origin, newValue)) {
                    break;
                }
            }
        }
    }


    private static void testAtomic() throws InterruptedException {

        final AtomicInt counter = new AtomicInt();
        final int threadCount = 10;
        final int countPerThread = 100000;

        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            threadPool.submit(() -> {
                for (int j = 0; j < countPerThread; j++) {
                    counter.incr();
                }
            });
        }
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
        threadPool.shutdown();

        System.out.println("expected : " + (threadCount * countPerThread));
        System.out.println("actual : " + counter.get());

    }
}