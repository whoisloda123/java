package com.liucan.utils.serialize;

import com.liucan.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liucan
 * @version 18-11-12
 */
@Component
public class Serialize {
    @Autowired
    private ObjectTransform objectTransform;

    public void example() {
        Person person = new Person();
        person.setAddress("重庆市酉阳县");
        person.setAge(28);
        person.setName("刘灿");
        person.setPassword("123456");

        try {
            //序列化
            byte[] bytes = objectTransform.serialize(person);

            //反序列化
            Person person1 = (Person) objectTransform.deserialize(bytes);
            System.out.println(person1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
