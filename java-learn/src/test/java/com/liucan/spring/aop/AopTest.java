package com.liucan.spring.aop;

import com.liucan.BaseJunit4Test;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/26
 * @brief
 */
public class AopTest extends BaseJunit4Test {
    @Autowired
    Aop aop;

    @Test
    public void testExample() {
        aop.example();
    }
}