package com.liucan.spring.aop.xml;

import org.aspectj.lang.JoinPoint;

/**
 * @author liucan
 * @date 2018/5/26
 * @brief
 */

public class Logging {
    /**
     * 方法执行之前
     */
    public void beforeAdvice(JoinPoint jp) {
        System.out.println("Going to setup student profile.");
    }

    /**
     * 方法执行之后，在调用后不等其返回
     */
    public void afterAdvice(JoinPoint jp) {
        System.out.println("Student profile has been setup.");
    }

    /**
     * 方法执行之后，等待其返回
     */
    public void afterReturningAdvice(Object retVal){
        System.out.println("Returning:" + retVal.toString() );
    }

    /**
     * 在方法抛出异常之后调用
     */
    public void AfterThrowingAdvice(IllegalArgumentException ex){
        System.out.println("There has been an exception: " + ex.toString());
    }
}
