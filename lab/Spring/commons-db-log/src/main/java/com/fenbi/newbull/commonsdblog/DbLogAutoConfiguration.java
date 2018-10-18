/**
 * @(#)DbLogAutoconfiguration.java, Aug 23, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.commonsdblog;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author pw
 */
@Configuration
@EnableAspectJAutoProxy
@ConditionalOnBean(value = JdbcTemplate.class, name = "dbLogJdbcTemplate")
public class DbLogAutoConfiguration {

    @Bean
    public DbLogAspect dbLogAspect(DbLogStorage dbLogStorage) {
        return new DbLogAspect(dbLogStorage);
    }

    @Bean
    public DbLogStorage dbLogStorage(@Qualifier("dbLogJdbcTemplate") JdbcTemplate jdbcTemplate) {
        return new DbLogStorage(jdbcTemplate);
    }
}