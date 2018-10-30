package com.liucan.reflect;

import com.liucan.pojo.Person;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 参考资料：
 * https://www.cnblogs.com/hrcnblogs/p/8711418.html
 * <p>
 * 反射
 * 1.Class类是反射的中心，可以通过Class类在运行时查找类的类型，方法属性等等,且调用任意一个类的方法，他的对象表示运行的一个类
 * 2.反射其实就是加载类，解剖出类的各个部分,并放到Class对象里面
 * 3.反射的一个重要引用就是实现动态代理
 * <p>
 * 动态代理
 * 1.相对于静态代理来说，利用代理模式，代理类和实现类都是写死的，在编译时刻就已经确定，缺点是如果有多个代理，则需要创建多个
 * 2.而动态代理则是在程序运行时刻，利用反射动态创建类和代理执行类的方法
 * 3.spring-aop则是基于动态代理实现的
 * 4.一般步骤
 * a.中介类：实现InvocationHandler的invoke接口,保存委托类引用，
 * b.在invoke实现中调用,实现对委托类方法调用，添加额外处理
 * c.通过Proxy类中的newProxyInstance方法得到代理类对象，然后通过代理类来调用委托类方法
 * d.注意:动态代理中，只实现了一个调用处理器，而没有真正实现代理类。代理类对象是通过Proxy类中的newProxyInstance方法得到的。
 * 这样，不管你在调用委托类任何方法时需要加入的额外操作都可以仅仅在调用处理器中的invoke方法中实现就可以了
 * e.代理类的创建，也是通过委托类的ClassLoader，反射来创建委托类的实例，相对于代理类也是继承委托类
 *
 * @author liucan
 * @version 18-10-27
 */
@Component
public class Reflect {

    @Data
    public class A {
        private String name;

        public A() {
        }

        public void test() {
            System.out.println("testA");
        }
    }

    public class B extends A {
        @Override
        public void test() {
            System.out.println("testB");
        }
    }

    private void proxy() {
        ICalculate calculate = new CalculateImpl();
        ICalculate proxy = (ICalculate) Proxy.newProxyInstance(ICalculate.class.getClassLoader(),
                new Class[]{ICalculate.class}, new ProxyHandler(calculate));
        proxy.add(1, 2);
        proxy.sub(2, 1);
        proxy.mul(1, 2);
        proxy.div(2, 1);
    }

    public void example() {
        try {
            //反射

            //获取类的class对象
            Class clazz = Person.class;
            //返回A类的所有为public 声明的构造方法
            Constructor[] cons = clazz.getConstructors();
            //返回A类所有的构造方法，包括private
            Constructor[] cons2 = clazz.getDeclaredConstructors();
            //返回A类的第一个public 方法
            Method m = clazz.getMethod("getName");
            //执行
            m.invoke(clazz.newInstance());
            //返回A类所有的public 方法
            Method[] ms = clazz.getMethods();
            //返回A类所有的方法，包括private
            Method[] allMs = clazz.getDeclaredMethods();
            //返回A类的public字段
            Field field = clazz.getDeclaredField("name");

            //动态代理
            proxy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
