package com.liucan.other;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/16
 * @brief
 */
public class OtherTest extends BaseJunit4Test {
    @Autowired
    Other other;

    @Test
    public void testTime() {
        other.time();
    }
}