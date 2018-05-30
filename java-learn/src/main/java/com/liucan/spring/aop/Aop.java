package com.liucan.spring.aop;

import com.liucan.spring.aop.aspect.School;
import com.liucan.spring.aop.noxml.ApplicationCfg;
import com.liucan.spring.aop.noxml.AspectUser;
import com.liucan.spring.aop.noxml.User;
import com.liucan.spring.aop.springapi.Sleep;
import com.liucan.spring.aop.xml.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
     * 参考：https://www.cnblogs.com/best/p/5736422.html
     *  https://blog.csdn.net/u014206695/article/details/61618034
     *  https://www.cnblogs.com/sprinng/p/5581552.html
     *
     * 一：面向方面的编程(AOP)
     * 1.Aspect(切面):通常是一个类，里面可以定义切入点和通知
     * 2.JointPoint(连接点):程序执行过程中明确的点，一般是方法的调用
     * 3.Advice(通知):AOP在特定的切入点上执行的增强处理，有before,after,afterReturning,afterThrowing,around
     * 4.Pointcut(切入点):就是带有通知的连接点，在程序中主要体现为书写切入点表达式
     * 5.AOP代理：AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可以使JDK动态代理，也可以是CGLIB代理，前者基于接口，后者基于子类
     *
     * 二：方式
     *  1.通过xml，<aop:config>方式
     *  2.通过@Aspect方式，这种方式比较方便
     *  3.简答的java对象方式
     *
     * 三：场景
     *  1.日志记录、审计、spring声明式事务(环绕通知)、安全性和缓存等
     *  2.环绕通知around,在目标方法完成前后做增强处理,环绕通知是最重要的通知类型,像事务,日志等都是环绕通知,
     *    注意编程中核心是一个ProceedingJoinPoint
     *  后续有时间详细看一下？
     *
     * 四：< aop:advisor>和< aop:aspect>区别
     *   1.<aop:aspect>实际上是定义横切逻辑，就是在连接点上做什么，在一个连接点的开始，结束，抛异常之后做什么
     *   2.<aop:advisor>则定义了在哪些连接点上应用什么<aop:aspect>，一般一个类继承多个before，after基类，然后可以重用
     *   3.这样做的好处就是可以让多个横切逻辑 （即<aop:aspect>定义的）多次使用，提供可重用性
     *   4.<aop:aspect>：用来定义切面，该切面可以包含多个切入点和通知，而且标签内部的通知和切入点定义是无序的；
     *   和advisor的区别就在此，advisor只包含一个通知和一个切入点。
     *
     * 五：框架和应用场景
     *   后续有时间一定要详细看一下，重中之重，包括JDK动态代理，应用场景，预编译方式和运行期动态代理实现程序功能的横向多模块统一控制？
     */
    public void example() {
        //xml方式
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

        //aspect方式
        System.out.println("aspect-----------------");

        school.setAge(123);
        school.setName("重庆市凤鸣山中学");
        school.setAddress("重庆市沙坪坝");

        school.getAddress();
        school.getAge();
        school.getName();
        try {
            school.printThrowException();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //简答的java对象方式
        Sleep sleep = (Sleep) context.getBean("sleep");
        sleep.sleeping();

        //noxml方式
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationCfg.class);
        ctx.refresh();
        AspectUser aspectUser = ctx.getBean(AspectUser.class);
        User user = ctx.getBean(User.class);
        user.show();
    }
}
