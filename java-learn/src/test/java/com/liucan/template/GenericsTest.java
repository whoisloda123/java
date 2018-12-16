package com.liucan.template;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @version 18-11-24
 */
public class GenericsTest extends BaseJunit4Test {
    @Autowired
    private Generics generics;

    @Test
    public void example() {
        generics.example();
    }
}