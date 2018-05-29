package com.liucan.spring.aop.noxml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author liucan
 * @date 2018/5/29
 * @brief
 */
@Aspect
//@Component
public class AspectUser {
    @Pointcut("execution(* com.liucan.spring.aop.noxml.User.show(..))")
    private void userPointcutId() {
    }

    @Before("userPointcutId()")
    public void beforeAdvice(JoinPoint jp) {
        System.out.println("user.show函数准备执行了");
    }

    @AfterReturning(value = "userPointcutId()", returning = "obj")
    public void AfterReturningAdvice(JoinPoint jp, Object obj) {
        System.out.println("user.show函数执行完了，返回：" + obj);
    }
}
