package com.liucan.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 参考：https://blog.csdn.net/briblue/article/details/73824058
 * 1.当开发者使用了Annotation 修饰了类、方法、Field 等成员之后，这些 Annotation 不会自己生效，
 * 必须由开发者提供相应的代码来提取并处理 Annotation 信息。这些处理提取和处理 Annotation 的代码统称为 APT（Annotation Processing Tool)
 * 2.注解内容的获取都是通过java反射来实现的
 * 3.注解中有一个名称为value的属性，且你只想设置value属性(即其他属性都采用默认值或者你只有一个value属性)，那么可以省略掉“value=”部分。
 * 4.元注解是可以注解到注解上的注解，元标签有 @Retention、@Documented、@Target、@Inherited、@Repeatable 5 种
 * 5.@Target生命周期，@Retention作用域,@Inherited被注解的类的子类也字段继承该注解
 * 6.可通过aop来实现对参数的传入控制
 *
 * @author liucan
 * @version 18-12-6
 */
@Component
public class AnnotationTest {
    @Autowired
    private ApplicationContext applicationContext;

    public void example() {
        UseAnnotation useAnnotation = applicationContext.getBean(UseAnnotation.class);

        useAnnotation.test(1, "2323");

        Class clazz = useAnnotation.getClass();
        TestAnnotation testAnnotation;
        String value;
        boolean b;
        //获取类注解
        if (clazz.isAnnotationPresent(TestAnnotation.class)) {
            testAnnotation = (TestAnnotation) clazz.getAnnotation(TestAnnotation.class);
            value = testAnnotation.value();
            b = testAnnotation.required();
        }

        try {
            //获取属性注解
            Field field = clazz.getDeclaredField("value");
            field.setAccessible(true);
            if (field.isAnnotationPresent(TestAnnotation.class)) {
                testAnnotation = (TestAnnotation) field.getAnnotation(TestAnnotation.class);
                value = testAnnotation.value();
                b = testAnnotation.required();
            }

            //获取方法注解
            Method method = clazz.getDeclaredMethod("test", Integer.class, String.class);
            method.setAccessible(true);
            Annotation[] methodAnnotations = method.getAnnotations();
            for (Annotation methodAnnotation : methodAnnotations) {
                testAnnotation = (TestAnnotation) methodAnnotation;
            }

            //获取参数上注解
            Parameter[] paramers = method.getParameters();
            for (Parameter paramer : paramers) {
                if (paramer.isAnnotationPresent(TestAnnotation.class)) {
                    testAnnotation = paramer.getAnnotation(TestAnnotation.class);
                }
                if (paramer.isAnnotationPresent(Value.class)) {
                    Value value1 = (Value) paramer.getAnnotation(Value.class);
                    value = value1.value();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
