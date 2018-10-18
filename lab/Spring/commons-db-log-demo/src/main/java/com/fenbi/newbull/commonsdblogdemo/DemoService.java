/**
 * @(#)TodoStorage.java, Aug 23, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.commonsdblogdemo;

import com.fenbi.newbull.commonsdblog.DbLog;
import org.springframework.stereotype.Service;

/**
 * @author pw
 */
@Service
public class DemoService {

    @DbLog(resource = "demoResource", action = "demoAction")
    public void sayHello() {
        System.out.println("hello foo.\n");
    }
}