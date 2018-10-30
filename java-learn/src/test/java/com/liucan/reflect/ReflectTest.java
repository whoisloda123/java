package com.liucan.reflect;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @version 18-10-27
 */
public class ReflectTest extends BaseJunit4Test {
    @Autowired
    private Reflect reflect;

    @Test
    public void example() {
        reflect.example();
    }
}