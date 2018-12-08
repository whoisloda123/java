package com.liucan.annotation;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @version 18-12-8
 */
public class AnnotationTestTest extends BaseJunit4Test {
    @Autowired
    private AnnotationTest annotationTest;

    @Test
    public void example() {
        annotationTest.example();
    }
}