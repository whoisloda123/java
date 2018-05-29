package com.liucan.spring.aop.springapi;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author liucan
 * @date 2018/5/29
 * @brief 通过sprig api方式实现aop
 */
public class SleepAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) {
        System.out.println("睡觉后要做美梦");
    }

    @Override
    public void before(Method method, Object[] objects, Object o) {
        System.out.println("睡觉前要敷面膜");
    }
}
