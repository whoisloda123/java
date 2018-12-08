package com.liucan.annotation;

import java.lang.annotation.*;

/**
 * @author liucan
 * @version 18-12-6
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TestAnnotation {
    String value();

    boolean required() default false;
}