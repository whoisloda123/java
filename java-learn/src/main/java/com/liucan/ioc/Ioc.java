package com.liucan.ioc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author liucan
 * @date 2018/5/20
 * @brief
 */

@Component
public class Ioc {
    /* *
     * 一.bean:
     * 作用域：
     *  1.singleton默认值，单例,创建起容器时就创建了
     *  2.prototype每次调用的时候，new出来，,创建起容器时没有创建
     * 应用场景：
     *  有状态的bean使用prototype，无状态的bean使用singleton作用域
     *  有一点很重要，即Spring不能对该Bean的整个生命周期负责,
     *  具有prototype作用域的Bean创建后交由调用者负责销毁对象回收资源
     *  后续有时间看一下？
     *
     * 生命周期：
     *  init-method 在实例化bean的时候立即调用
     *  destroy-method 在容器中移除bean之后调用
     *  default-init-method 有太多具有相同名称的初始化，不需要在每一个bean上声明初始化方法和销毁方法 在beans上面加
     *  default-destroy-method 同理
     *
     * 后置处理器：
     *  后续有时间看一下？
     *
     * 二.依赖注入DI
     * 方式：
     *  1.基于构造函数注入
     *   容器调用带有一组参数的类构造函数时，基于构造函数的 DI 就完成了，其中每个参数代表一个对其他类的依赖
     *  2.基于setter方法
     *
     * 三.beans自动装配
     *  1.byName:bean里面的属性autowire="byName"，根据名字自动寻找beans的id注入到属性
     *  2.byType:bean里面的属性autowire="byType"，根据名字自动寻找beans的类型注入到属性
     *  3.byConstructor:bean里面的属性autowire="byName"，根据名字自动寻找beans的id注入到构造函数
     *
     * 四.注解
     *  概念：
     *   不采用XML的beans方式注入，而是使用注解来配置依赖注入
     *  方式：
     *   1.@Required 注释
     *    用于bean属性的setter方法，受影响的bean属性在配置时必须放在XML配置文件中，
     *    否则容器就会抛出一个BeanInitializationException 异常
     *   2.@Autowired 注释
     *    a.用于类setter方法，会自动连接bean，可去掉bean里面的属性配置，会在setter方法中自动byType注入
     *    b.用于类属性，可去掉类setter方法,当为自动连接属性传递的时候，Spring 会将这些传递过来的值或者引用自动分配给那些属性
     *    c.用于构造函数，当创建bean时，XML文件中没有使用元素配置bean，构造函数也会被自动连接
     */
    public void example() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("com/liucan/ioc/resources/Beans.xml");

        //beans
        HelloWorld objA = (HelloWorld)context.getBean("helloWorld");
        objA.getMessage1();
        objA.getMessage2();

        HelloIndia objB = (HelloIndia) context.getBean("helloIndia");
        objB.getMessage1();
        objB.getMessage2();
        objB.getMessage3();

        //di
        TextEditor te = (TextEditor) context.getBean("textEditor");
        te.spellCheck();

        HelloWorld objC = te.getHelloWorld();
        HelloIndia objIndia = te.getHelloIndia();
        HelloChina objChina = te.getHelloChina();

        //注入集合
        CollectionBean collectionBean = (CollectionBean) context.getBean("collectionBean");

        //注解
        Annotation annotation = (Annotation) context.getBean("annotation");

        //确保正常关闭，并且调用相关的 destroy 方法
        //context.registerShutdownHook(); 是一个钩子方法，当jvm关闭退出的时候会调用这个钩子方法
        context.registerShutdownHook();
    }
}
