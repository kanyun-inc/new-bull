package com.fenbi.newbull.hellojetty.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {

    String value() default "";

    String[] method() default {};

}
