package com.liucan.log;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/7/3
 * @brief
 */
public class LogTest extends BaseJunit4Test {
    @Autowired
    private Log log;

    @Test
    public void testExample() {
        log.example();
    }
}