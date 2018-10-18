/**
 * @(#)DbLogStorage.java, Aug 23, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.commonsdblog;

import org.springframework.jdbc.core.JdbcOperations;

/**
 * @author pw
 */
public class DbLogStorage {

    private final JdbcOperations db;

    public DbLogStorage(JdbcOperations db) {
        this.db = db;
    }

    public void create(String resource, String action, String className, String methodName) {
        db.update("insert into `log` (resource, `action`, className, methodName, createdTime) values (?, ?, ?, ?, ?)",
                resource, action,
                className, methodName, System.currentTimeMillis());
    }
}