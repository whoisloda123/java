package com.liucan.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liucan
 * @date 2018/6/30
 * @brief
 */
@Data
public class Person implements Serializable {
    /**
     * 进行序列化时候最好指定这个UID
     */
    private static final long serialVersionUID = -766571861519198043L;

    private String name; //姓名
    private Integer age; //年龄
    private String address; //家庭地址
    private transient String password; //不能被序列化
}
