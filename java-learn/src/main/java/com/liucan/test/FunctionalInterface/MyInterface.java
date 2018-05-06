package com.liucan.test.FunctionalInterface;

@FunctionalInterface
public interface MyInterface {
    void run(int a);

    //默认方法，让接口可以有实现的函数体
    //但是为什么要在接口里面加这个了？后面有时间看一下
    default public void method() {
        System.out.println("method in interface");
    }
}
