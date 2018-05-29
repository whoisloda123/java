package com.liucan.spring.ioc.xml;

import lombok.Data;

/**
 * @author liucan
 * @date 2018/5/20
 * @brief
 */
@Data
public class HelloWorld {
    private String message1;
    private String message2;

    public void init() {
        System.out.println("HelloWorld init ");
    }

    public void destroy() {
        System.out.println("HelloWorld destroy");
    }
}
