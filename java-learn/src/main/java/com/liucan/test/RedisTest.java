package com.liucan.test;

import com.liucan.pojo.Student;
import com.liucan.redis.Ledis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RedisTest {
    private final Ledis ledis;

    @Autowired
    public RedisTest(Ledis ledis) {
        this.ledis = ledis;
    }

    public void testAll() {
        //set
        String value = ledis.get("age");
        ledis.set("age", "36");
        value = ledis.get("age");
        //批量set
        ledis.mset("age", "37", "money", "120");
        List<String> strings = ledis.mget("age", "money");

        //map
        ledis.hset("hset", "1", "1");
        ledis.hset("hset", "2", "2");
        value = ledis.hget("hset", "2");
        Map<String, String> stringStringMap = ledis.hgetall("hset");

        //object map
        Student student = new Student();
        student.setAddress("中国四川");
        student.setAge(11);
        student.setEmail("13131312@qq.com");
        student.setId(1);
        student.setSex("male");
        student.setName("刘灿");
        ledis.hsetObject("hset", "3", student);

        Object object = ledis.hgetObject("hset", "3");
        Student student1 = (Student)object;

        //set
        student = new Student();
        student.setAddress("中国重庆");
        student.setAge(12);
        ledis.saddObject("saddObject", student);
        student = new Student();
        student.setAddress("中国广州");
        student.setAge(13);
        ledis.saddObject("saddObject", student);

        List<Object> objects = ledis.smembersAllObject("saddObject");
        for (Object obj : objects) {
            Student stu = (Student)obj;
            System.out.println(stu);
        }
    }
}
