package com.fenbi.newbull.hellojetty.server.controller;

import com.fenbi.newbull.hellojetty.annotation.MyController;
import com.fenbi.newbull.hellojetty.annotation.MyRequestMapping;
import com.fenbi.newbull.hellojetty.annotation.MyRequestParam;
import com.fenbi.newbull.hellojetty.exception.NotFoundException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@MyController
@Slf4j
public class HelloController {

    @Data
    @Builder
    public static class Greeting {
        private long createdTime;
        private String message;
        private String user;
    }

    private Map<String, Greeting> greetingMap = new HashMap<>();

    @MyRequestMapping(value = "/hello", method = "GET")
    Greeting get(@MyRequestParam String user) {
        if (!greetingMap.containsKey(user)) {
            throw new NotFoundException();
        }
        return greetingMap.get(user);
    }

    @MyRequestMapping(value = "/hello", method = "POST")
    void add(@MyRequestParam String user,
        @MyRequestParam String message) {
        Greeting greeting = Greeting.builder().user(user).message(message)
            .createdTime(System.currentTimeMillis()).build();
        greetingMap.put(user, greeting);
    }

}
