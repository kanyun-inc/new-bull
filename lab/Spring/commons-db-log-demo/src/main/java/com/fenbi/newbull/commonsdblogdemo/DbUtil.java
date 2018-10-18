/**
 * @(#)DbUtil.java, Aug 23, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.commonsdblogdemo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * 一个工具类，创建一个基于内存数据库的 JdbcTemplate。同学们也可以用本地 MySQL 的 JdbcTemplate
 *
 * @author pw
 */
public class DbUtil {

    public static JdbcTemplate create() {
        DataSource ds = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("testdb;mode=MySQL")
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql").build();

        return new JdbcTemplate(ds);
    }
}