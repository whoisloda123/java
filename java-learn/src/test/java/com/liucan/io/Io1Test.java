package com.liucan.io;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @version 19-3-10
 */
public class Io1Test extends BaseJunit4Test {
    @Autowired
    private Io1 io1;

    @Test
    public void test1() {
        io1.test();
    }
}