package com.liucan.template;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考：https://blog.csdn.net/s10461/article/details/53941091
 * <p>
 * 泛型通配符:?
 * <p>
 * 泛型上下边界:
 * ? extends T（上）:类型适合需要get比较多的地方，因为所以的类都有T的方法，而set就不知道到底是那个类的set
 * ? super T（下）:类型适合需要set比较多的地方，因为所以类的set方法都是有的，而get就不知道是用那个类的get
 *
 * @author liucan
 * @version 18-11-24
 */
@Component
public class Template {

    public void example() {
        Integer[] integers = {1, 2, 3};
        String[] strings = {"1", "2", "3"};
        TemplateFunction.printArray(integers);
        TemplateFunction.printArray(strings);

        TemplateFunction.maximum(3, 4);
        TemplateFunction.maximum("sfs", "3Fs");

        //<? extends T>
        List<? extends Number> list1 = new ArrayList<Integer>();
        List<? extends Number> list2 = new ArrayList<Double>();
        List<? extends Number> list3 = new ArrayList<Number>();

        //<? super T>
        List<? super Integer> list4 = new ArrayList<>();
        List<? super Integer> list5 = new ArrayList<Number>();
        List<? super Integer> list6 = new ArrayList<Object>();

        TemplateClass<Integer, String> templateClass = new TemplateClass<>();
        templateClass.put(1, "1");
        String value = templateClass.get(1);

        TemplateFunction.upDown(new TemplateClass<Integer, String>());
        TemplateFunction.upDown(new TemplateClass<Number, String>());
        //TemplateFunction.upDown(new TemplateClass<String, String>()); 会编译错误
    }

}
