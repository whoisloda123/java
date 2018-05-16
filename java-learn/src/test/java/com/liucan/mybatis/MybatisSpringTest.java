package com.liucan.mybatis;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/16
 * @brief
 */
public class MybatisSpringTest extends BaseJunit4Test {
    @Autowired
    Mybatis mybatis;

    @Test
    public void testExample() {
        mybatis.example();
    }
}