package com.liucan.transients;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/9/24
 * @brief
 */
public class TransientsTest extends BaseJunit4Test {
    @Autowired
    Transients transients;

    @Test
    public void testExample() {
        transients.example();
    }
}