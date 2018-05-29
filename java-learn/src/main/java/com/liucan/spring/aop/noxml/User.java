package com.liucan.spring.aop.noxml;

/**
 * @author liucan
 * @date 2018/5/29
 * @brief
 */
//@Component
public class User {
    public String show() {
        System.out.println("一个用户对象");
        return "一个用户对象";
    }
}
