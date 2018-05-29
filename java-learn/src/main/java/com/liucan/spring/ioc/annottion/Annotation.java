package com.liucan.spring.ioc.annottion;

import com.liucan.classload.Classload;
import com.liucan.spring.ioc.xml.HelloChina;
import com.liucan.spring.ioc.xml.HelloIndia;
import com.liucan.spring.ioc.xml.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author liucan
 * @date 2018/5/20
 * @brief
 */
public class Annotation {
    private HelloChina helloChina;
    //用于类属性，可去掉类setter方法,当为自动连接属性传递的时候，Spring会将这些传递过来的值或者引用自动分配给那些属性
    @Autowired
    @Qualifier("helloIndia1")
    private HelloIndia helloIndia;
    private HelloWorld helloWorld;

    //用于构造函数，当创建bean时，XML文件中没有使用元素配置bean，构造函数也会被自动连接
    @Autowired
    public Annotation(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    //用于bean属性的setter方法，自动连接bean，可去掉bean里面的属性配置，会在setter方法中自动byType注入
    @Autowired
    public void setHelloChina(HelloChina helloChina) {
        this.helloChina = helloChina;
    }

    /**
     * 1.默认情况下使用 @Autowired 注释进行自动注入时，Spring 容器中匹配的候选 Bean 数目必须有且仅有一个
     *   找不到一个匹配的Bean时，将抛出BeanCreationException异常并指出必须至少拥有一个匹配的Bean，此配置则不会抛异常
     * 2.一般仅会在开发期或测试期碰到（如为了快速启动 Spring 容器，仅引入一些模块的 Spring 配置文件）
     */
    @Autowired(required = false)
    public void setClassload(Classload classload) {

    }

    //和init-method="init"一样的效果，在实例化bean的时候立即调用
    @PostConstruct
    public void init1() {
        System.out.println("HelloWorld init1 ");
    }

    //destroy-method="destroy一样的效果，在容器中移除bean之后调用
    @PreDestroy
    public void close1() {
        System.out.println("HelloWorld close1 ");
    }
}
