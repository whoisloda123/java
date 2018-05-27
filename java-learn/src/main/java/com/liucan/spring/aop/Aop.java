package com.liucan.spring.aop;

import com.liucan.spring.aop.aspect.School;
import com.liucan.spring.aop.xml.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author liucan
 * @date 2018/5/26
 * @brief aop
 */
@Component
public class Aop {
    @Autowired
    private School school;
    /**
     * 一：面向方面的编程(AOP)
     *  1.前置通知:方法执行之前，执行通知
     *  2.后置通知:方法执行之后，不考虑其结果，执行通知
     *  3.返回后通知:方法执行之后，只有在方法成功完成时，才能执行通知
     *  4.抛出异常后通知:方法执行之后，只有在方法退出抛出异常时，才能执行通知
     *  5.环绕通知:方法调用之前和之后，执行通知
     *
     * 二：方式
     *  1.通过xml，<aop:config>方式
     *  2.通过@Aspect方式，这种方式比较方便
     *
     * 三：场景
     *  日志记录、审计、声明式事务、安全性和缓存等
     *  后续有时间详细看一下？
     *
     * 四：框架和应用场景
     *  后续有时间一定要详细看一下？
     */
    public void example() {
        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("com/liucan/spring/resources/AopBeans.xml");
        Student student = (Student) context.getBean("student");
        student.getName();
        student.getAge();
        try {
            student.printThrowException();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("aspect-----------------");

        school.setAge(123);
        school.setName("重庆市凤鸣山中学");

        school.getAge();
        school.getName();
        school.printThrowException();
    }
}
