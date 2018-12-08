package com.liucan.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author liucan
 * @version 18-12-8
 */
@Component
public class UseAnnotation {
    private Integer value;

    @TestAnnotation(value = "123", required = true)
    public void test(@TestAnnotation("123") Integer value, @Value("123") String value1) {
        String value2 = value1;
    }
}
