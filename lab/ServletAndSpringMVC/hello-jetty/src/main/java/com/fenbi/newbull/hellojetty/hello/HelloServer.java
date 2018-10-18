package com.fenbi.newbull.hellojetty.hello;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

import static org.eclipse.jetty.servlet.ServletContextHandler.SESSIONS;

/**
 * 一个简单的Jetty Server例子，监听8888端口，直接加载了一个Servlet。
 *
 * @author fankai
 */
//@Service
@Slf4j
public class HelloServer implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        Server server = new Server(8888);

        ServletContextHandler helloHandler = new ServletContextHandler(SESSIONS);
        helloHandler.setContextPath("/hello");
        helloHandler.addServlet(HelloServlet.class, "/*");
        helloHandler.addFilter(HelloFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {helloHandler});
        server.setHandler(handlers);
        server.start();
        log.info("hello server started");
        server.join();
    }

}

