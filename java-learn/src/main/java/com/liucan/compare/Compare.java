package com.liucan.compare;

import com.liucan.pojo.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class Compare {
    public void example() {
        /* *
         * Comparator和Comparable比较
         * Comparable:
         * 排序接口,实现了Comparable接口，意味该类本身支持排序，直接通过Arrays.sort() 或 Collections.sort()进行排序
         * Comparator:
         * 比较器接口,包括两个函数：compare() 和 equals(),类实现Comparator接口，就是一个“比较器”。其它的类可以根据该比较器去排序
         * 综上所述：
         * Comparable是内部比较器，而Comparator是外部比较器
         * 类本身实现了Comparable比较器，它本身支持排序；若它本身没实现Comparable，或者是final，也可以通过外部比较器Comparator进行排序
         * http://www.cnblogs.com/skywang12345/p/3324788.html
         * https://blog.csdn.net/u013256816/article/details/50899416
         */
        //Comparable
        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setName("liucan");
        list.add(student);
        student = new Student();
        student.setName("zhouwenmei");
        list.add(student);
        student = new Student();
        student.setName("canliu");
        list.add(student);
        Collections.sort(list);

        //Comparator
        list = new ArrayList<>();
        student = new Student();
        student.setName("liucan");
        list.add(student);
        student = new Student();
        student.setName("zhouwenmei");
        list.add(student);
        student = new Student();
        student.setName("canliu");
        list.add(student);
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        list = new ArrayList<>();
        student = new Student();
        student.setName("liucan");
        list.add(student);
        student = new Student();
        student.setName("zhouwenmei");
        list.add(student);
        student = new Student();
        student.setName("canliu");
        list.add(student);
        list.sort(Comparator.comparing(e -> e.getName()));

        //方法引用，完全是为了简化λ表达式的
        Comparator<Integer> c2 = (x, y) -> (x < y) ? -1 : ((x == y) ? 0 : 1);

        //c3和c1是一样的效果:静态方法
        Comparator<Integer> c3 = (x, y) -> Integer.compare(x ,y);
        Comparator<Integer> c1 = Integer::compare;

        //下面两句是一样的：实例方法引用
        list.forEach(e -> e.getName());
        list.forEach(Student::getName);
    }
}
