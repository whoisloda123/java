package com.liucan.utils.serialize;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @version 18-11-12
 */
public class SerializeTest extends BaseJunit4Test {
    @Autowired
    private Serialize serialize;

    @Test
    public void testExample() {
        serialize.example();
    }
}