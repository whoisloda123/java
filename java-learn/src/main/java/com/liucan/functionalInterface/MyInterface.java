package com.liucan.functionalInterface;

@java.lang.FunctionalInterface
public interface MyInterface {
    void run(int a);

    /**
     * 默认方法，让接口可以有实现的函数体
     * 但是为什么要在接口里面加这个了？
     * 以前创建了一个接口，并且已经被大量的类实现。
     * 如果需要再扩充这个接口的功能加新的方法，就会导致所有已经实现的子类需要重写这个方法。
     * 如果在接口中使用默认方法就不会有这个问题。
     * 所以从 JDK8 开始新加了接口默认方法，便于接口的扩展。
     */
    default void method() {
        System.out.println("method in interface");
    }
}
