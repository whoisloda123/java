package com.liucan.json;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/16
 * @brief
 */
public class JsonTest extends BaseJunit4Test {
    @Autowired
    Json json;

    @Test
    public void testExample() {
        json.example();
    }
}