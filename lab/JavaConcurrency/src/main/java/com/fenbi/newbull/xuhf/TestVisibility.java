/**
 * @(#)TestVisibility.java, Jul 21, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.xuhf;

/**
 * @author xuhongfeng
 */
public class TestVisibility {

    public static void main(String[] args) throws InterruptedException {

        int i = 0;
        while (true) {
            i++;
            System.out.println("round: " + i);
            test(i);
        }
    }

    public static void test(int round) throws InterruptedException {

        final Worker workerA = new Worker("A", round);
        final Worker workerB = new Worker("B", round);

        Thread t1 = new Thread(() -> {
            workerB.stop("t1");
            workerA.startWorking("t1");
        });

        Thread t2 = new Thread(() -> {
            workerA.stop("t2");
            workerB.startWorking("t2");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static class Worker {

        private boolean running = true;

        private final int round;
        private final String name;

        public Worker(String name, int round) {
            this.name = name;
            this.round = round;
        }

        public void stop(String callThread) {
            System.out.println("round:" + round + ", workerName:" + name  + ", callThread:" + callThread + ", " + "call stop");
            running = false;
        }

        public void startWorking(String callThread) {
            System.out.println("round:" + round + ", workerName:" + name  + ", callThread:" + callThread + ", " + "start working");
            while (isRunning()) {
                // do some work
            }
            System.out.println("round:" + round + ", workerName:" + name  + ", callThread:" + callThread + ", " + "end working");
        }

        private boolean isRunning() {
            return running;
        }
    }
}