package com.liucan.ioc;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author liucan
 * @date 2018/5/22
 * @brief
 */

@Configurable
public class HelloConfig {
    @Bean
    public HelloJapan helloJapan() {
        return new HelloJapan();
    }

    @Bean
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }
}
