package com.liucan.redis;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/16
 * @brief
 */
public class RedisTest extends BaseJunit4Test {
    @Autowired
    Redis redis;

    @Test
    public void testExample() {
        redis.example();
    }
}