package com.liucan.spring.aop.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author liucan
 * @date 2018/5/26
 * @brief
 */
@Aspect
@Component
public class AspectModule {
    /**
     * 声明一个切入点,有2个部分
     * 1.一个切入点表达式决定了我们感兴趣的哪个方法会真正被执行
     * 2.一个切入点标签包含一个名称和任意数量的参数。方法的真正内容是不相干的，并且实际上它应该是空的
     */
    @Pointcut("execution(* com.liucan.spring.aop.aspect.School.getName(..))")
    private void aspectId() {}

    //aspectId()为pointcutId
    @Before("aspectId()")
    public void doBeforeTask() {
        System.out.println("函数准备执行了");
    }

    @After("aspectId()")
    public void doAfterTask() {
        System.out.println("函数开始执行了");
    }

    @AfterReturning(pointcut = "aspectId()", returning = "retVal")
    public void doAfterReturningTask(Object retVal) {
        System.out.println("函数执行完成了，返回值:"+ retVal);
    }

    @AfterThrowing(pointcut = "aspectId()", throwing = "ex")
    public void doAfterThrowingTask(Exception ex) {
        System.out.println("函数抛异常了:"+ ex);
    }

    //@Around("aspectId()")
    public void doAroundTask() {
        System.out.println("函数开始和结束都会执行一遍");
    }

    /**
     * 指定设置某个切人点，然后执行函数
     */
    @Before("execution(* com.liucan.spring.aop.aspect.School.getAge(..))")
    public void doBeforeGetAge() {
        System.out.println("准备开始执行getAge");
    }

    /**
     * 捕获printThrowException抛出的异常,如果函数里面已经捕获了异常，则该切点捕获不到
     */
    @AfterThrowing(pointcut = "execution(* com.liucan.spring.aop.aspect.School.printThrowException(..))", throwing = "ex")
    public void doAfterThrowingTask1(Exception ex) {
        System.out.println("捕获到printThrowException抛出的异常了" + ex);
    }
}
