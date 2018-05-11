package com.liucan.pojo;

import lombok.Data;

import java.io.Serializable;

/* *
 * 继承Comparable表示该类可以内部排序
 */
@Data
public class Student implements Serializable, Comparable<Student> {
    private Integer id;
    private String name;
    private Integer age;
    private String address;
    private String email;
    private String sex;

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.getName());
    }
}
