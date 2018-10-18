/**
 * @(#)DemoCommonsActor.java, Jul 26, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.xuhf;

import com.fenbi.actor.Looper;
import com.fenbi.actor.LooperGroup;
import com.fenbi.actor.impl.AbstractActor;
import com.fenbi.actor.impl.ThreadLooperGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhongfeng
 */
public class DemoCommonsActor {

    public void test() {
        LooperGroup looperGroup = new ThreadLooperGroup(Runtime.getRuntime().availableProcessors());

        AccountActor[] actors = buildActors();

        for (AccountActor actor : actors) {
            Looper looper = looperGroup.next();
            actor.startLoop(looper);
        }
    }

    static class AccountActor extends AbstractActor {

        // 状态
        private final Map<Integer, Account> accounts = new HashMap<>();


        public void transfer(int fromUserId, int toUserId, int money) {
            send(new ActorLoopEvent() {
                @Override
                protected void doHandle() {
                    accounts.get(fromUserId).money -= money;
                    accounts.get(toUserId).money += money;
                }
            });
        }
    }


    static AccountActor[] buildActors() {
        return null;
    }

    class Account {
        private int money;
    }
}