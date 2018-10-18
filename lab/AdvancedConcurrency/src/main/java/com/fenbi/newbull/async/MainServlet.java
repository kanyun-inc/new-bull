/**
 * @(#)MainServlet.java, Jul 25, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.async;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xuhongfeng
 */
@WebServlet(asyncSupported = true)
public class MainServlet extends HttpServlet  {

    private final ClientPool clientPool = new ClientPool();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        int fromUserId = 1, toUserId = 2;
        transfer(req, resp, fromUserId, toUserId);

        if (System.currentTimeMillis()  - startTime > 100) {
            throw new RuntimeException("Timeout");
        }
    }

    /**
     *  将 fromUserId 的 1/2 钱转给 toUserId, http 返回 toUserId的余额 字符串.
     *  如果有error, http 返回 "Error" 字符串
     */
    private void transfer(HttpServletRequest req, HttpServletResponse resp, int fromUserId, int toUserId) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().append("Hello World");
    }

}
