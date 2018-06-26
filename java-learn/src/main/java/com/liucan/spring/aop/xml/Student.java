package com.liucan.spring.aop.xml;

/**
 * @author liucan
 * @date 2018/5/26
 * @brief
 */
public class Student {
    private Integer age;
    private String name;

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        System.out.println("Age : " + age );
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        System.out.println("Name : " + name );
        return name;
    }

    public void printThrowException(){
        System.out.println("Student exception raised");
        throw new IllegalArgumentException();
    }
}
