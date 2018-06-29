package com.liucan.pojo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/* *
 * 继承Comparable表示该类可以内部排序
 */
@Data
public class Student implements Serializable, Comparable<Student> {
    @Max(value = 120, message = "id不能查过120")
    private Integer id;
    private String name;
    @Min(value = 18, message = "未满18岁")
    private Integer age;
    private String address;
    private String email;
    private String sex;

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.getName());
    }
}
