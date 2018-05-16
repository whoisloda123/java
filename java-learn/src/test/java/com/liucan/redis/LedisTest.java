package com.liucan.redis;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class LedisTest extends BaseJunit4Test {
    @Autowired
    Ledis ledis;

    @Test
    //控制事务配置
    @Transactional(transactionManager = "transactionManager")
    //不回滚，操作的数据才不会污染数据库
    @Rollback(value = false)
    public void set() {
        ledis.set("1", "2");
        System.out.println("1121");
    }
}