package com.liucan.serialize;

import com.liucan.pojo.Country;
import com.liucan.pojo.Person;
import com.liucan.util.Constants;
import com.liucan.util.SerializeUtil;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 参考：https://www.cnblogs.com/sharkli/p/5607895.html
 *       https://blog.csdn.net/Leon_cx/article/details/81517603
 * a.必须实现Serializable接口或Externalizable接口的类才能进行序列化
 * b.transient和static修饰符修饰的成员变量不会参与序列化和反序列化
 * c.反序列化对象和序列化前的对象的全类名和serialVersionUID必须一致,反序列的时候是通过序列化版本号serialVersionUID判断类是否一致
 *   如果被修改了则会异常，如果类不指定会通过属性方法等默认生成，最好不用默认，指定生成一个，这样不会报错
 * d.在目标类中添加私有的writeObject和readObject方法可以覆盖默认的序列化和反序列化方法,
 *   objectOutputStream的writeObject和ObjectInputStream的readObject里面会通过反射的方式调用类的private的writeObject和readObject方法
 * e.在目标类中添加私有的readResolve可以最终修改反序列化回来的对象，可用于单例模式防止序列化导致生成第二个对象的问题
 *
 * @author liucan
 * @version 18-11-12
 */
@Component
public class Serialize {

    public void example() {
        Person person = new Person();
        person.setAddress("重庆市酉阳县");
        person.setAge(28);
        person.setName("刘灿");
        person.setPassword("123456");
        Country country = new Country();
        country.setName("中国");
        country.setPeople((long) 123456);
        person.setCountry(country);

        try {
            //深拷贝
            Person person2 = (Person) person.clone();
            Person person3 = (Person) person.clone1();

            //序列化
            byte[] bytes = SerializeUtil.objectSerializer(person);

            //反序列化
            Person person1 = (Person) SerializeUtil.objectDeserialize(bytes);
            System.out.println(person1);

            String path = Constants.TMP_PATH + "person.ser";
            try (FileOutputStream fileOutputStream = new FileOutputStream(path);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
                objectOutputStream.writeObject(person);
                System.out.println("写入person.ser成功:" + person);
            }

            try (FileInputStream fileInputStream = new FileInputStream(path);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Person person4 = (Person) objectInputStream.readObject();
                System.out.println("读取person.ser成功:" + person4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
