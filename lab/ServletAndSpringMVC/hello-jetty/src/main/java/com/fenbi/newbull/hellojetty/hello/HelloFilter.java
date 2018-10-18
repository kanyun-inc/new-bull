/**
 * @(#)HelloServlet.java, Feb 24, 2017.
 * <p>
 * Copyright 2017 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.hellojetty.hello;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fankai
 */
@Slf4j
public class HelloFilter implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("before handle");
        CharResponseWrapper wrapper = new CharResponseWrapper(
                (HttpServletResponse)response);
        chain.doFilter(request, wrapper);
        response.getWriter().write("filtered " + wrapper.toString());
        log.info("after handle");
    }

    @Override
    public void destroy() {

    }


    public static class CharResponseWrapper extends HttpServletResponseWrapper {
        private CharArrayWriter output;

        @Override
        public String toString() {
            return output.toString();
        }

        public CharResponseWrapper(HttpServletResponse response) {
            super(response);
            output = new CharArrayWriter();
        }

        @Override
        public PrintWriter getWriter() {
            return new PrintWriter(output);
        }
    }
}