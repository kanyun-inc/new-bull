/**
 * @(#)HelloServlet.java, Feb 24, 2017.
 * <p>
 * Copyright 2017 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.hellojetty.server;

import com.fenbi.newbull.hellojetty.annotation.MyController;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * TODO: DispatchServlet是实现功能的核心组件，需要实现路由、参数解析、输出格式化等功能
 *
 * @author fankai
 */
@Slf4j
@Component
public class DispatchServlet extends HttpServlet {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 请求入口
        String path = ((Request) req).getHttpURI().getPath();
        String method = req.getMethod();
        Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(MyController.class);

        // TODO: 处理路由等逻辑

    }
}