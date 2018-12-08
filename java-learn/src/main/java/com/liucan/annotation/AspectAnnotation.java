package com.liucan.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author liucan
 * @version 18-12-8
 */
@Aspect
@Component
public class AspectAnnotation {

    @Around("@annotation(com.liucan.annotation.TestAnnotation)")
    private Object doAroundTask(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        Method method = ((MethodSignature) jp.getSignature()).getMethod();

        if (method.getAnnotation(TestAnnotation.class).required()) {
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                if (parameter.isAnnotationPresent(Value.class)) {
                    Value value = parameter.getAnnotation(Value.class);
                    args[i] = value.value();
                }
            }
        }
        return jp.proceed(args);
    }
}
