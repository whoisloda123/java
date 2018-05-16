package com.liucan.stream;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/16
 * @brief
 */
public class Stream1Test extends BaseJunit4Test {
    @Autowired
    Stream1 stream1;

    @Test
    public void testExample() {
        stream1.example();
    }
}