package com.liucan.compare;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/16
 * @brief
 */
public class CompareTest extends BaseJunit4Test {
    @Autowired
    Compare compare;

    @Test
    public void testExample() {
        compare.example();
    }
}