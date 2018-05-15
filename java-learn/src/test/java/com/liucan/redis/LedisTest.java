package com.liucan.redis;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LedisTest extends BaseJunit4Test {
    @Autowired
    Ledis ledis;

    @Test
    public void set() {
        ledis.set("1", "2");
        System.out.println("1121");
    }
}