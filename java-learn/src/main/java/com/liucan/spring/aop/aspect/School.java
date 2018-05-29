package com.liucan.spring.aop.aspect;

import org.springframework.stereotype.Component;

/**
 * @author liucan
 * @date 2018/5/26
 * @brief
 */
@Component
public class School {
    private String name;
    private Integer age;
    private String address;

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        System.out.println("Age : " + age);
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        System.out.println("Name : " + name);
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        System.out.println("Address : " + address);
        return address;
    }

    public void printThrowException(){
        System.out.println("School Exception raised");
        throw new IllegalArgumentException();
    }
}
