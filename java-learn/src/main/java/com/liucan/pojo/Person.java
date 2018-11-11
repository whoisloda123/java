package com.liucan.pojo;

import lombok.Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    private String name = "liucan"; //姓名
    private Integer age; //年龄
    private String address; //家庭地址
    private transient String password; //不能被序列化

    /**
     * 序列化方法，手动序列化
     * ObjectOutputStream的writeObject会通过反射的方式调用类的private的writeObject和readObject方法
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(age);
        out.writeObject(name);
    }

    /**
     * 反序列化，手动序列化
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        age = in.readInt();
        name = (String) in.readObject();
    }
}
