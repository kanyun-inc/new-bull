/**
 * @(#)HelloServlet.java, Feb 24, 2017.
 * <p>
 * Copyright 2017 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.hellojetty.hello;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fankai
 */
public class HelloServlet extends HttpServlet
{
    @Override
    protected void doGet( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        String name = request.getParameter("name");
        response.getWriter().println("<h1>Hello " + name + "</h1>");
        response.getWriter().println("powered by " + getClass());
    }
}