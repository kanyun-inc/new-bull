/**
 * @(#)Starter.java, Jul 25, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

/**
 * @author xuhongfeng
 */
@SpringBootApplication
public class Starter extends SpringBootServletInitializer {

    @Bean
    public Servlet dispatcherServlet() {
        return new MainServlet();
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Starter.class);
        // To disabled web environment, change `true` to `false`
        application.setWebEnvironment(true);
        application.run(args);
    }
}