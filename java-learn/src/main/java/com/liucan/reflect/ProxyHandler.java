package com.liucan.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 可以在执行handler里面加一下自己的处理
 *
 * @author liucan
 * @version 18-10-29
 */
public class ProxyHandler implements InvocationHandler {
    private Object proxy;

    public ProxyHandler(Object proxy) {
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始执行方法：" + method.getName());
        Object object = method.invoke(this.proxy, args);
        System.out.println("结束执行方法：" + method.getName() + "结果：" + object);
        return object;
    }
}
