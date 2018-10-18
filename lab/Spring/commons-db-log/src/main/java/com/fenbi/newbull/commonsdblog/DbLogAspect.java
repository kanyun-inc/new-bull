/**
 * @(#)DbLogAspect.java, Aug 23, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.commonsdblog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author pw
 */
@Aspect
public class DbLogAspect {

    private final DbLogStorage dbLogStorage;

    public DbLogAspect(DbLogStorage dbLogStorage) {
        this.dbLogStorage = dbLogStorage;
    }

    @Around("@annotation(DbLog)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DbLog annotation = signature.getMethod().getAnnotation(DbLog.class);

        dbLogStorage
                .create(annotation.resource(), annotation.action(), signature.getMethod().getDeclaringClass().getName(),
                        signature.getMethod().getName());

        return joinPoint.proceed();
    }
}