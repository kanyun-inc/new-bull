/**
 * @(#)DemoServiceTest.java, Aug 29, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.commonsdblogdemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author pw
 */
// 起一个 SpringBoot 的测试环境
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDbLogDemoApplication.class)
public class DemoServiceTest {

    @Autowired
    private DemoService demoService;

    @Autowired
    @Qualifier("dbLogJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void sayHello() {
        // 清空数据
        jdbcTemplate.update("delete from `log`");

        demoService.sayHello();

        // 检查 sayHello 是否在数据库里插入了正确的数据
        Map<String, Object> result = jdbcTemplate.queryForMap("select * from `log`");

        // 校验数据是否和预期的一致
        Assert.assertEquals("demoResource", result.get("resource")); // annotation 传入的
        Assert.assertEquals("demoAction", result.get("action")); // annotation 传入的
        Assert.assertEquals("sayHello", result.get("methodName")); // 反射获取的方法名
        Assert.assertEquals("com.fenbi.newbull.commonsdblogdemo.DemoService", result.get("classname")); // 反射获取的类名
    }
}