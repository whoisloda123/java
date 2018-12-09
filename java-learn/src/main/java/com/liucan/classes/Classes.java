package com.liucan.classes;

import org.springframework.stereotype.Component;

/**
 * java嵌套类包括
 * 1.匿名类
 * 2.局部类(本地类)：声明局部变量的任何地方声明的类。局部类与局部变量具有相同的范围
 * 3.成员类（内部类）:是封闭类的成员，可以调用封闭类的成员变量，和平台成员变量是一样的,内部类成员只能在内部类中访问，并且可能不被外部类使用
 * 4.静态成员类:封闭类的静态成员,和普通静态成员是一样的作用
 * <p>
 * 一个java文件里面可以有多个类，但只能有一个public类，并且public的类名必须与文件名相一致，
 * 和public是属于同一级别的关系，编译的时候会生成多个class文件
 * 也可以没有public类
 *
 * @author liucan
 * @version 18-12-9
 */
@Component
public class Classes {

    private void example() {
        //匿名类
        new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        }.run();

        //局部类
        ItemManager itemManager = new ItemManager(2);
        itemManager.add(Item.of("刘灿", "好帅啊！"));
        itemManager.add(Item.of("周雯媚", "好漂亮啊！"));

        Iterator iterator = itemManager.iterator();
        while (iterator.hasMoreElements()) {
            System.out.println(iterator.nextElement());
        }

        //静态成员类
        ItemManager.ItemList1 itemList1 = new ItemManager.ItemList1();
    }
}
