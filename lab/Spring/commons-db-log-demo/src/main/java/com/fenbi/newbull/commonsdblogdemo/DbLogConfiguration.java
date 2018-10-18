/**
 * @(#)DbLogConfiguration.java, Aug 29, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.commonsdblogdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author pw
 */
@Configuration
public class DbLogConfiguration {

    @Bean
    public JdbcTemplate dbLogJdbcTemplate() {
        return DbUtil.create();
    }
}