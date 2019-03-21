package com.liucan.designpattern.creativepattern.singleton;

/**
 * 饿汉式单例模式
 * 线程安全
 *
 * @author liucan
 * @version 19-3-21
 */
public class HungrySingleton {
    private final static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
