package com.liucan.designpattern.creativepattern.singleton;

/**
 * 懒汉式，在需要的时候才初始化
 * 必须要加上volatile和synchronized否则会有多线程问题
 * 缺点：加锁影响性能
 *
 * @author liucan
 * @version 19-3-21
 */
public class LazySingleton {
    private static volatile LazySingleton instance = null;

    private LazySingleton() {
    }

    public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
