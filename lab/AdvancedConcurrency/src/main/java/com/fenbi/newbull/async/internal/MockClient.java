/**
 * @(#)MockClient.java, Jul 26, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.async.internal;

import com.fenbi.common.util.ThreadUtils;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuhongfeng
 */
public class MockClient {

    private final ExecutorService threadPool = Executors.newCachedThreadPool();
    private final Map<Integer, Integer> userAccounts = new ConcurrentHashMap<>();

    public MockClient() {
        userAccounts.put(1, 1024);
    }

    public void query(int userId, QueryCallback callback) {
        threadPool.submit(() -> {
            ThreadUtils.sleepQuietly(1000L);
            int money = userAccounts.getOrDefault(userId, 0);
            callback.onSuccess(money);
        } );
    }

    public void transfer(int fromUserId, int toUserId, int money, TransferCallback callback) {
        threadPool.submit(() -> {
            ThreadUtils.sleepQuietly(1000L);
            boolean ranBool = new Random().nextBoolean();
            if (ranBool) {
                callback.onFailed(new RuntimeException("mock failed"));
            } else {
                synchronized (userAccounts) {
                    int fromMoney = userAccounts.getOrDefault(fromUserId, 0);
                    if (fromMoney < money) {
                        callback.onFailed(new RuntimeException("not enough money"));
                        return;
                    }
                    userAccounts.put(fromUserId, fromMoney - money);
                    int toMoney = userAccounts.getOrDefault(toUserId, 0) + money;
                    userAccounts.put(toUserId, toMoney);
                    callback.onSuccess();
                }
            }
        } );
    }

    public interface  QueryCallback {

        void onSuccess(int money);

        void onFailed(Exception error);
    }

    public interface TransferCallback {

        void onSuccess();

        void onFailed(Exception error);
    }
}