package com.liucan.io;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @version 19-3-14
 */
public class Nio1Test extends BaseJunit4Test {
    @Autowired
    private Nio1 nio1;

    @Test
    public void test1() {
        nio1.test();
    }
}