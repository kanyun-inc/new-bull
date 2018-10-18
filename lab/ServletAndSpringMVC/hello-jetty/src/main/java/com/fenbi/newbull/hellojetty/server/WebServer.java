package com.fenbi.newbull.hellojetty.server;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import static org.eclipse.jetty.servlet.ServletContextHandler.SESSIONS;

/**
 * 一个功能更加强大的Web Server，加载了DispatchServlet，主要的工作由DispatchServlet完成
 *
 * @author fankai
 */
@Service
@Slf4j
public class WebServer implements CommandLineRunner {

    @Autowired
    private DispatchServlet dispatchServlet;

    @Override
    public void run(String... strings) throws Exception {
        Server server = new Server(8888);

        ServletContextHandler handler = new ServletContextHandler(SESSIONS);
        handler.setContextPath("/");
        handler.addServlet(new ServletHolder(dispatchServlet), "/*");

        server.setHandler(handler);
        server.start();
        log.info("web server started");
        server.join();
    }

}

