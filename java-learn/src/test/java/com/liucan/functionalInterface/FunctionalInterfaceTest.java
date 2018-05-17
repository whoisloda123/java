package com.liucan.functionalInterface;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/16
 * @brief
 */
public class FunctionalInterfaceTest extends BaseJunit4Test {
    @Autowired
    FunctionalInterface functionalInterface;

    @Test
    public void testExample() {
        functionalInterface.example();
    }
}