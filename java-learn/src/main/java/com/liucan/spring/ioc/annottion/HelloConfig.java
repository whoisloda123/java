package com.liucan.spring.ioc.annottion;

import com.liucan.spring.ioc.xml.HelloJapan;
import com.liucan.spring.ioc.xml.HelloWorld;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author liucan
 * @date 2018/5/22
 * @brief 通过java的方式添加bean, 和xml方式是一样的
 */

@Configurable //类似<beans/>
public class HelloConfig {
    @Bean
    public HelloJapan helloJapan() {
        return new HelloJapan();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }
}
