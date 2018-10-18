/**
 * @(#)Log.java, Aug 23, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.commonsdblog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对应的方法执行时，在数据库中添加相应的记录，数据库的结构参考 schema.sql
 *
 * @author pw
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbLog {

    /**
     * 对应数据库中的 resource 字段
     *
     * @return
     */
    String resource();

    /**
     * 对应数据库中的
     *
     * @return
     */
    String action();
}
