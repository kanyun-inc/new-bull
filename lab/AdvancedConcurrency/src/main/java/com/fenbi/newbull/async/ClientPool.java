/**
 * @(#)ClientPool.java, Jul 26, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.async;

import com.fenbi.common.util.ThreadUtils;
import com.fenbi.newbull.async.internal.MockClient;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuhongfeng
 */
public class ClientPool {

    private final MockClient client = new MockClient();
    private final AtomicInteger borrowCounter = new AtomicInteger(0);

    public ClientPool() {

        new Thread(() -> {
            while (true) {
                checkLeak();
                ThreadUtils.sleepQuietly(1000L);
            }
        }).start();
    }

    public MockClient borrowClient() {
        borrowCounter.incrementAndGet();
        return client;
    }

    public void returnClient(MockClient mockClient) {
        borrowCounter.decrementAndGet();
    }

    public void checkLeak() {
        if (borrowCounter.get() != 0) {
            System.out.println("maybe client Leak !");
        }
    }
}