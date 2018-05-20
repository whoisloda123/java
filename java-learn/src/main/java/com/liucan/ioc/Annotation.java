package com.liucan.ioc;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/20
 * @brief
 */
public class Annotation {
    private HelloChina helloChina;
    //用于类属性，可去掉类setter方法,当为自动连接属性传递的时候，Spring 会将这些传递过来的值或者引用自动分配给那些属性
    @Autowired
    private HelloIndia helloIndia;
    private HelloWorld helloWorld;

    //用于构造函数，当创建bean时，XML文件中没有使用元素配置bean，构造函数也会被自动连接
    @Autowired
    public Annotation(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    //用于bean属性的setter方法，自动连接 bean，可去掉bean里面的属性配置，会在setter方法中自动byType注入
    @Autowired
    public void setHelloChina(HelloChina helloChina) {
        this.helloChina = helloChina;
    }
}
