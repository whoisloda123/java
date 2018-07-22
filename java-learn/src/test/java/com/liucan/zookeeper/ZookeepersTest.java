package com.liucan.zookeeper;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/7/22
 * @brief
 */
public class ZookeepersTest extends BaseJunit4Test {
    @Autowired
    private Zookeepers zookeepers;

    @Test
    public void testExample() {
        zookeepers.example();
    }
}