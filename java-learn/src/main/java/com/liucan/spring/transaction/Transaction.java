package com.liucan.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author liucan
 * @date 2018/5/27
 * @brief spring事务管理
 */
@Component
public class Transaction {
    @Autowired
    private StatementTransactionByAnno statementTransactionByAnno;

    /** 参考：https://blog.csdn.net/bao19901210/article/details/41724355
     * 一.事务的概念
     *  1.原子性：事务应该当作一个单独单元的操作，这意味着整个序列操作要么是成功，要么是失败的
     *  2.一致性：这表示数据库的引用完整性的一致性，表中唯一的主键等
     *  3.隔离性：可以同时处理很多有相同的数据集的事务，每个事务应该与其他事务隔离，以防止数据损坏
     *  4.持久性：一个事务一旦完成全部操作后，这个事务的结果必须是永久性的，不能因系统故障而从数据库中删除
     *
     * 二.spring事务管理类型
     *  1.编程式事务管理：编程的帮助下有管理事务。极大的灵活性，但却很难维护
     *   a.通过PlatformTransactionManager类或TransactionTemplate类提供的commit和rollback来进行管理
     *
     *  2.声明式事务管理：业务代码中分离事务管理。使用注释或 XML 配置来管理事务，一般用这种方式
     *   过程：
     *    a.使用标它创建一个事务处理的建议，同时，我们定义一个匹配所有方法的切入点，我们希望这些方法是事务型的并且会引用事务型的建议
     *    b.如果在事务型配置中包含了一个方法的名称，那么创建的建议在调用方法之前就会在事务中开始进行
     *    c.目标方法会在 try / catch 块中执行
     *    d.如果方法正常结束，AOP 建议会成功的提交事务，否则它执行回滚操作
     *   方式：
     *    a.基于tx和aop名字空间的xml配置文件
     *    b.基于@Transactional注解(简单易用，更清爽)
     *
     * 三.事务特性
     *   a.事务隔离级别:隔离级别是指若干个并发的事务之间的隔离程度
     *   b.事务传播行为:在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为
     *   c.事务超时:指一个事务所允许执行的最长时间，如果超过该时间限制但事务还没有完成，则自动回滚事务
     *   d.事务只读属性:默认为读写事务
     *
     * 四.事务回滚规则
     *   a.默认只有抛出unchecked异常时才回滚事务，就是抛出的异常为RuntimeException的子类(Errors也会导致事务回滚)，
     *     抛出checked异常不会事务回滚。可以明确的配置在抛出那些异常时回滚事务，包括checked异常。也可以明确定义那些异常抛出时不回滚事务
     *
     * 五.注意事项
     *   a.@Transactional注解只被应用到public方法上,在其他类型方法上使用，被忽略不会抛出任何异常。
     *   b.默认情况下，只有来自外部的方法调用才会被AOP代理捕获，内部方法调用类内部的其他方法并不会引起事务行为，
     *     即使被调用方法使用@Transactional注解进行修饰
     *   c.作用于类上时，该类的所有 public 方法将都具有该类型的事务属性
     *
     *  后面有时间一定详细看一下？
     */
    public void example() {
        //编程式事务管理
        ApplicationContext context =
                new ClassPathXmlApplicationContext("com/liucan/spring/resources/Transaction.xml");
        ProgrammingTransaction programmingTransaction = (ProgrammingTransaction)context.getBean("programmingTransaction");
        programmingTransaction.example();

        //声明式事务管理（xml方式）
        try {
            StatementTransactionByXml statementTransactionByXml = (StatementTransactionByXml)context.getBean("statementTransactionByXml");
            statementTransactionByXml.example();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //声明式事务管理（注解方式）
        try {
            statementTransactionByAnno.insert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
