/**
 * @(#)Test.java, Aug 27, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.netprogramming.sample;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author xuhongfeng
 */
public class TestEchoServer {

    @Test
    public void testBlockingEchoServer() throws Exception {
        _test(new BlockingEchoServer());
    }

    @Test
    public void testNonBlockingEchoServer() throws Exception {
        _test(new NonBlockingEchoServer());
    }

    @Test
    public void testIOMultiplexEchoServer() throws Exception {
        _test(new IOMultiplexEchoServer());
    }

    @Test
    public void testMultiThreadEchoServer() throws Exception {
        _test(new MultiThreadEchoServer());
    }

    @Test
    public void testThreadPoolEchoServer() throws Exception {
        _test(new ThreadPoolEchoServer());
    }

    @Test
    public void testReactorEchoServerV1() throws Exception {
        _test(new ReactorEchoServerV1());
    }

    @Test
    public void testReactorEchoServerV2() throws Exception {
        _test(new ReactorEchoServerV2());
    }

    private void _test(EchoServer server) throws Exception {

        int port =  new Random().nextInt(60000) + 1000;
        server.start(port);

        int threadCount = 100;
        Future[] futures = new Future[threadCount];
        for (int i = 0; i < threadCount; i++) {
            final String msg = "task-" + i;
            FutureTask<Boolean> task = new FutureTask<>(() -> {
                String result = EchoClient.sendAndReceive(port, msg);
                return result.equals(msg);
            });
            futures[i] = task;

            new Thread(task).start();
        }

        for (Future<Boolean> f : futures) {
            Assert.assertTrue(f.get());
        }
    }
}