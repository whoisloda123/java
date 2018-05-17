package com.liucan.statics;

import com.liucan.BaseJunit4Test;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StaticsTest extends BaseJunit4Test {
    @Autowired
    public Statics statics;

    @Test
    public void testExample() {
        statics.example();
    }
}