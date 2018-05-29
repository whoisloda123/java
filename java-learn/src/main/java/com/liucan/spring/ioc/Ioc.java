package com.liucan.spring.ioc;

import com.liucan.spring.ioc.annottion.Annotation;
import com.liucan.spring.ioc.annottion.HelloConfig;
import com.liucan.spring.ioc.xml.*;
import com.liucan.spring.springevent.publisher.EventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
     * 生命周期：
     *  init-method 在实例化bean的时候立即调用
     *  destroy-method 在容器中移除bean之后调用
     *  default-init-method 有太多具有相同名称的初始化，不需要在每一个bean上声明初始化方法和销毁方法 在beans上面加
     *  default-destroy-method 同理
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
     *    d.@Autowired(required = false) 在默认情况下使用@Autowired，Spring容器中匹配的候选Bean数目必须有且仅有一个。
     *      找不到一个匹配的Bean时，将抛出BeanCreationException异常并指出必须至少拥有一个匹配的Bean,此配置则不会抛异常
     *    e.required = false一般仅会在开发期或测试期碰到（如为了快速启动 Spring 容器，仅引入一些模块的 Spring 配置文件）
     *   3.@Qualifier注释
     *    配合@Autowired使用，有多个类型一样但是id不一样的bean时候指定哪一个真正的 bean装配
     *
     * 五.基于java注解
     *  @Configuration 和 @Bean 注解
     *   1.@Configuration 的注解类表示这个类可以使用 Spring IoC 容器作为 bean 定义的来源。
     *     @Bean 注解告诉 Spring，一个带有 @Bean 的注解方法将返回一个对象，该对象应该被注册为在 Spring 应用程序上下文中的 bean
     *   2.其效果和在xml里面加bean一样的
     *
     * 六.spring事件
     *  1.Spring 的核心是 ApplicationContext,负责管理 beans 的完整生命周期,加载 beans 时，ApplicationContext 发布某些类型的事件
     *    如：ContextStartedEvent，ContextStoppedEvent等等
     *  2.通过 ApplicationEvent 类和 ApplicationListener 接口来提供在 ApplicationContext 中处理事件
     *  3. Spring 的事件处理是单线程的，所以如果一个事件被发布，直至并且除非所有的接收者得到的该消息，该进程被阻塞并且流程将不会继续
     *  4.可自定义spring事件，继承ApplicationEvent，ApplicationEventPublisher用来发布
     *    后面有时间看一下他的用处？
     *
     *  七.框架和应用场景
     *   后续有时间一定要详细看一下？ BeanFactory和ApplicationContext的主要区别？
     */
    public void example() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("com/liucan/spring/resources/IocBeans.xml");

        //start springevent.
        context.start();

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

        //基于java注解
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(HelloConfig.class);
        ctx.refresh();
        HelloJapan helloJapan = ctx.getBean(HelloJapan.class);

        //自定义spring
        EventPublisher cvp = (EventPublisher) context.getBean("customEventPublisher");
        cvp.publishCustomEvent();

        //确保正常关闭，并且调用相关的 destroy 方法
        //context.registerShutdownHook(); 是一个钩子方法，当jvm关闭退出的时候会调用这个钩子方法
        context.registerShutdownHook();

        //stop springevent.
        context.stop();
    }
}
