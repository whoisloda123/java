package com.liucan.statics;

import org.springframework.stereotype.Component;

/* *
 * 参考：http://www.cnblogs.com/dolphin0520/p/3799052.html
 *      https://www.cnblogs.com/dotgua/p/6354151.html?utm_source=itdadao&utm_medium=referral
 *      https://blog.csdn.net/newjerryj/article/details/8650268
 * static的4种用法
 * 1.静态成员
 * 2.静态函数
 * 3.静态块
 *  1.static{}块在类加载(请看类加载classload包)的时候执行且仅会被执行一次
 *  2.用来初始化静态变量和调用静态方法，用来优化程序性能，如只需进行一次初始化
 *  3.static{}块其实和static成员变量一样的,参考redis包里面的LedisBase的static块
 * 4.导入其他包里面的静态方法和变量 import static com.liucan.statics.staticFather.*;
 *
 * 静态方法和静态变量都可以通过对象访问,但是不建议
 * static是不允许用来修饰局部变量
 */
@Component
public class Statics {
    public void example() {
        try {
            Class.forName("com.liucan.statics.staticFather");
            Class.forName("com.liucan.statics.staticFather");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
