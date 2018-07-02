package com.liucan.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author liucan
 * @date 2018/7/1
 * @brief 基础配置, 替代spring-base.xml,spring-dao.xml配置
 * 1.@Configuration:相当于xml里面的beans
 * 2.@EnableAspectJAutoProxy：开启AOP代理自动配置，和<aop:aspectj-autoproxy/>一样的功能
 * 3.@ComponentScan：扫描注解类，和<context:component-scan base-package="com.liucan.*"/>一样的功能
 * 4.@EnableTransactionManagement: 开启注解事务管理，和<tx:annotation-driven>一样的功能
 * 5.@Import:将指定的类实例注入之Spring IOC
 * 6.@EnableWebMvc:相当于xml里面的<mvc:annotation-driven/>，配置spring-mvc注解驱动
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.liucan"})
public class AppConfig {
    /**
     * 定义视图解析器
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }
}
