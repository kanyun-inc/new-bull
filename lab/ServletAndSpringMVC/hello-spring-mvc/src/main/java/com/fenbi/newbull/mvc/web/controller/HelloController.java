package com.fenbi.newbull.mvc.web.controller;

import com.fenbi.newbull.mvc.web.data.User;
import com.fenbi.newbull.mvc.web.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String hello(
            @RequestParam(defaultValue = "0") User user) {
        if (user == null) {
            throw new BadRequestException("illegal user");
        }
        return "hello " + user;
    }

    @GetMapping("/hello/{name}")
    public String hello1(@PathVariable String name) {
        return "hello " + name;
    }

    @GetMapping("/bye")
    public String bye(
            @RequestParam(defaultValue = "0") User user) {
        return "bye " + user.getId();
    }

}
