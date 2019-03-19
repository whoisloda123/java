package com.liucan.designpattern;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @version 19-3-19
 */
public class DesignPatternTest extends BaseJunit4Test {
    @Autowired
    private DesignPattern designPattern;

    @Test
    public void test1() {
        designPattern.test();
    }
}