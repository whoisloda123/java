package com.liucan.utils.serialize;

import com.liucan.pojo.Country;
import com.liucan.pojo.Person;
import org.springframework.stereotype.Component;

/**
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
