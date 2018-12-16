package com.liucan.template;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考：https://blog.csdn.net/briblue/article/details/76736356
 * http://www.cnblogs.com/tiancai/p/7449207.html
 * 泛型通配符:?
 * 泛型上下边界:
 * ? extends T（上）:类型适合需要get比较多的地方，因为所以的类都有T的方法，而set就不知道到底是那个类的set
 * ? super T（下）:类型适合需要set比较多的地方，因为所以类的set方法都是有的，而get就不知道是用那个类的get
 *
 * 类型擦除：
 * 1.Java 的泛型在编译器有效，在运行期被删除，也就是说所有泛型参数类型在编译成class文件后都会被清除掉
 * 2.<T>被擦除后,会被泛型转译为Object，指定了类型上限会被转译为类型上限， <T extends Number>会被泛型转译为Number，且编译器生成的时候会插入强制的类型转换
 * 3.如果你要在反射中找到 add 对应的 Method，你应该调用 getDeclaredMethod("add",Object.class) 否则程序会报错，
 *   提示没有这么一个方法，原因就是类型擦除的时候，T 被替换成 Object 类型了
 * 4.可以利用类型擦除的原理，用反射的手段就绕过了正常开发中编译器不允许的操作限制
 * 5.因为类型擦除机制，不能new T ,擦除后变成new Object，显然有问题，不能T[] array = new T[]也是一样的道理，数组要记住实际的类型，而擦除后是Object
 *   而可以new ArrayList<T>，因为ArrayList里面维护了一个非泛型Object[]数组
 *
 * @author liucan
 * @version 18-11-24
 */
@Component
public class Generics {

    public void example() {
        Integer[] integers = {1, 2, 3};
        String[] strings = {"1", "2", "3"};
        GenericsFunction.printArray(integers);
        GenericsFunction.printArray(strings);

        GenericsFunction.maximum(3, 4);
        GenericsFunction.maximum("sfs", "3Fs");

        //<? extends T>
        List<? extends Number> list1 = new ArrayList<Integer>();
        List<? extends Number> list2 = new ArrayList<Double>();
        List<? extends Number> list3 = new ArrayList<Number>();

        //<? super T>
        List<? super Integer> list4 = new ArrayList<>();
        List<? super Integer> list5 = new ArrayList<Number>();
        List<? super Integer> list6 = new ArrayList<Object>();

        GenericClass<Integer, String> genericClass = new GenericClass<>();
        genericClass.put(1, "1");
        String value = genericClass.get(1);

        GenericsFunction.upDown(new GenericClass<Integer, String>());
        GenericsFunction.upDown(new GenericClass<Number, String>());
        //GenericsFunction.upDown(new GenericClass<String, String>()); 会编译错误

        try {
            List<Integer> list = new ArrayList<>();
            List<String> list7 = new ArrayList<>();
            boolean m = list.getClass() == list7.getClass();

            //利用泛型和反射可以绕过编译器报错调用一些方法
            list.add(1);
            //list.add("23");编译器会报错

            Method method = list.getClass().getDeclaredMethod("add", Object.class);
            method.invoke(list, "2");
            method.invoke(list, 1);
            //此处list相对于List<?>,可以放任何类型
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
