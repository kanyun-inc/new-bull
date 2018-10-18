package com.fenbi.newbull.mvc.web.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 名字为/**的bean，会被spring mvc的BeanNameUrlHandlerMapping注册
 */
@Component("/hello-by-bean-name")
public class HelloByBeanNameController extends AbstractController  {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView model = new ModelAndView("hello"); // 会被重定向到 /hello
        return model;
    }
}
