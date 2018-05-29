package com.liucan.spring.aop.noxml;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author liucan
 * @date 2018/5/29
 * @brief 零配置实现Spring IoC与AOP
 * 1.@Configurable:用于表示当前类为容器的配置类，类似<beans/>
 * 2.@ComponentScan:扫描的范围<context:component-scan/>
 * 3.@EnableAspectJAutoProxy:自动代理<aop:aspectj-autoproxy proxy-target-class="true"/>
 */
@Configurable
@ComponentScan(basePackages = "com.liucan.spring.aop.noxml")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ApplicationCfg {
    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public AspectUser aspectUser() {
        return new AspectUser();
    }
}
