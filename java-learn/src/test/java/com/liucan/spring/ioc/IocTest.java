package com.liucan.spring.ioc;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/20
 * @brief
 */
public class IocTest extends BaseJunit4Test {
    @Autowired
    Ioc ioc;

    @Test
    public void testExample() {
        ioc.example();
    }
}