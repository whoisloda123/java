package com.liucan.transients;

import com.liucan.pojo.Person;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author liucan
 * @date 2018/9/24
 *
 * 1.变量前加上transient关键字，则对象序列化的时候忽略该属性
 * 2.场景：在一些用户敏感信息，如密码等需要序列化被传输和保存的时候
 * 3.transient关键字只能修饰变量，不能修饰方法和类
 * 4.静态变量不管是否被transient修饰，均不能被序列化，被反序列化的时候，静态变量可以获取，但是是从jvm里面获取的
 * 5.被transient修饰的关键字其实也可以被序列化的，对象的序列化可以通过实现两种接口来实现，若实现的是Serializable接口，
 *   则所有的序列化将会自动进行，若实现的是Externalizable接口，则没有任何东西可以自动序列化，需要在writeExternal方法中进行手工指定所要序列化的变量，
 *   这与是否被transient修饰无关
 * 6.参考资料：http://www.cnblogs.com/lanxuezaipiao/p/3369962.html
 */
@Component
public final class Transients {

    public void example() {
        Person person = new Person();
        person.setAddress("纳格兰");
        person.setAge(123);
        person.setName("食人魔法师");
        person.setPassword("123456");
        System.out.println(person);
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:/user.txt"))) {
            os.writeObject(person);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(("C:/user.txt")))) {
            Person person1 = (Person) is.readObject();
            System.out.println(person1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
