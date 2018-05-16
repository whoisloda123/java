package com.liucan;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/* *
 * Junit为java提供的单元测试框架
 * SpringTest对其做了封装
 * 1.不用重新启动容器
 * 2.可用spring的特性ioc
 * 3.对数据库操作有回滚处理，防止测试数据
 */

//使用SpringJUnit4ClassRunner作为Junit测试环境,调用测试方法
@RunWith(SpringJUnit4ClassRunner.class)
//加载spring配置文件
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
//控制事务配置（此处和下面一起用），可在测试类的方法上加
@Transactional(transactionManager = "transactionManager")
//自动回滚，操作的数据才不会污染数据库，可在测试类的方法上加
@Rollback(value = true)
public class BaseJunit4Test {
    @BeforeClass
    public static void beforeClass() {
        System.out.println("junit test begin:");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("junit test end:");
    }

    @Before
    public void before() {
        printMethodName();
    }

    @After
    public void after() {
        System.out.println("test fun end：");
    }

    private void printMethodName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        System.out.println(e.getMethodName() + "--------------------------------------------------");
    }
}
