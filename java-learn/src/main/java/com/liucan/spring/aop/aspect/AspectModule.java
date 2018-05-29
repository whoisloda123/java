package com.liucan.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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
    private void pointcutId() {
    }

    //pointcutId()为pointcutId
    @Before("pointcutId()")
    public void doBeforeTask(JoinPoint jp) {
        System.out.println("函数准备执行了");
    }

    @After("pointcutId()")
    public void doAfterTask(JoinPoint jp) {
        System.out.println("函数开始执行了");
    }

    @AfterReturning(pointcut = "pointcutId()", returning = "retVal")
    public void doAfterReturningTask(JoinPoint jp, Object retVal) {
        System.out.println("函数执行完成了，返回值:"+ retVal);
    }

    @AfterThrowing(pointcut = "pointcutId()", throwing = "ex")
    public void doAfterThrowingTask(JoinPoint jp, Exception ex) {
        System.out.println("函数抛异常了:"+ ex);
    }

    /**
     * 增强处理方法体内，调用ProceedingJoinPoint参数的procedd()方法才会执行目标方法
     */
    @Around("execution(* com.liucan.spring.aop.aspect.School.getAddress(..))")
    public Object doAroundTask(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("执行目标方法之前,可以通过getArgs()对参数修改");
        Object obj = jp.proceed();
        System.out.println("执行目标方法之后可对其进行再次修改：" + obj);
        return obj;
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
