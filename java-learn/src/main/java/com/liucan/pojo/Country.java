package com.liucan.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Country implements Serializable {
    private String name;
    private Long people;
    private Integer age;
}
