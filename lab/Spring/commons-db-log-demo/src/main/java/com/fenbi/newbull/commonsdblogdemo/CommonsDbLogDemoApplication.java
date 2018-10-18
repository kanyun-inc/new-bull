package com.fenbi.newbull.commonsdblogdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonsDbLogDemoApplication implements CommandLineRunner {

    private final DemoService demoService;

    @Autowired
    public CommonsDbLogDemoApplication(DemoService demoService) {
        this.demoService = demoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CommonsDbLogDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        demoService.sayHello();
    }
}