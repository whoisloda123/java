package com.liucan.pojo;

import com.liucan.util.SerializeUtil;
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
public class Person implements Serializable, Cloneable {
    /**
     * 进行序列化时候最好指定这个UID
     */
    private static final long serialVersionUID = -766571861519198043L;

    private String name = "liucan"; //姓名
    private Integer age; //年龄
    private String address; //家庭地址
    private transient String password; //不能被序列化
    private Country country;

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

    /**
     * 可用于单例模式反序列化时生成新的对象
     * JVM从内存中反序列化地"组装"一个新对象时,就会自动调用这个 readResolve方法来返回我们指定好的对象了, 单例规则也就得到了保证.
     */
    private Object readResolve() {
        // instead of the object we're on,
        // return the class variable INSTANCE
        return this;

    }

    /**
     * 深拷贝：先将对象序列化然后再反序列化
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            byte[] bytes = SerializeUtil.objectSerializer(this);
            return SerializeUtil.objectDeserialize(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CloneNotSupportedException(e.getMessage());
        }
    }

    /**
     * 深拷贝：先调用默认拷贝函数super.clone获取新对象，然后对对象引用变量拷贝
     */
    @Deprecated
    public Object clone1() throws CloneNotSupportedException {
        Person person = (Person) super.clone();
        Country countryNew = new Country();
        Country countryOld = person.getCountry();
        if (countryOld != null) {
            countryNew.setPeople(countryOld.getPeople());
            countryNew.setName(countryOld.getName());
        }
        person.setCountry(countryNew);
        return person;
    }
}
