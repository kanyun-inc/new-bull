/**
 * @(#)MyResponseStatus, Aug 31, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.fenbi.newbull.hellojetty.annotation;

import org.eclipse.jetty.http.HttpStatus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fankai
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyResponseStatus {

    int value() default HttpStatus.INTERNAL_SERVER_ERROR_500;

}
