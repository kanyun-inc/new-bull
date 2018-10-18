/**
 * @(#)EchoServer.java, Aug 27, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.netprogramming.sample;

import java.io.IOException;

/**
 *
 * IO 模型 示例:
 *  阻塞IO : {@link BlockingEchoServer}
 *  非阻塞IO : {@link NonBlockingEchoServer}
 *  IO复用 : {@link IOMultiplexEchoServer}
 *
 *  线程模型示例:
 *   单线程 : {@link BlockingEchoServer}
 *   多线程 : {@link MultiThreadEchoServer}
 *   线程池 : {@link ThreadPoolEchoServer}
 *   单线程Reactor : {@link ReactorEchoServerV1}
 *   多线程Reactor : {@link ReactorEchoServerV2}
 *
 * @author xuhongfeng
 */
public interface EchoServer {

    void start(int port) throws IOException;
}