/**
 * @(#)ByteBufferTest.java, Sep 05, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.netprogramming.sample;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.ByteBuffer;

/**
 * @author xuhongfeng
 */
public class ByteBufferTest {

    /**
     * 一般流程:
     *
     * 1. 初始化时， position = 0, limit = capacity
     * 2. ReadFromChannel, 即ByteBuffer.put.  position 增长.
     * 3. Flip.  limit=position, position=0
     * 4. WriteToChannel, 即ByteBuffer.get. position 增长
     * 5. Clear. reset position=0, limit=capacity
     *
     */
    @Test
    public void testByteBuf() {
        int capacity = 100;
        ByteBuffer buffer = ByteBuffer.allocate(capacity);

        /**
         * 1. 初始化
         */
        Assert.assertEquals(buffer.position(), 0);
        Assert.assertEquals(buffer.limit(), capacity);

        /**
         * 2. Put
         */
        buffer.putInt(1);
        buffer.putInt(2);
        Assert.assertEquals(buffer.position(), 8);
        Assert.assertEquals(buffer.limit(), capacity);
        Assert.assertEquals(buffer.remaining(), capacity - 8); // 剩下多少空间可以 Put

        /**
         * 3. Flip
         */
        buffer.flip();
        Assert.assertEquals(buffer.position(), 0);
        Assert.assertEquals(buffer.limit(), 8);

        /**
         * 4. Get
         */
        buffer.getInt();
        Assert.assertEquals(buffer.position(), 4);
        Assert.assertEquals(buffer.limit(), 8);
        Assert.assertEquals(buffer.remaining(), 4); // 剩下多少数据可以 Get

        /**
         * 5. clear
         */
        buffer.clear();
        Assert.assertEquals(buffer.position(), 0);
        Assert.assertEquals(buffer.limit(), capacity);
    }
}