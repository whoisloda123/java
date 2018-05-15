package com.liucan;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/* *
 * Junit为java提供的单元测试框架
 * SpringTest对其做了封装
 * 1.不用重新启动容器
 * 2.可用spring的特性ioc
 * 3.对数据库操作有回滚处理，防止测试数据
 */

//使用junit4.SpringJUnit4ClassRunner作为Junit测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//加载spring配置文件
@ContextConfiguration(locations = {"classpath:spring/spring-base.xml", "classpath:spring/spring-mybatis.xml"})
//控制事务配置（此处和下面一起用），也在测试类的方法上
@Transactional
//这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），
// 同时//指定自动回滚（defaultRollback = true）这样做操作的数据才不会污染数据库！
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BaseJunit4Test {
    @BeforeClass
    public static void beforeClass() {
        System.out.println("单元测试开始：");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("单元测试结束");
    }

    @Before
    public void before() {
        System.out.println("before：");
    }

    @After
    public void after() {
        System.out.println("after：");
    }
}
