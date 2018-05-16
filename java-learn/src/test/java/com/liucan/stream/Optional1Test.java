package com.liucan.stream;

import com.liucan.BaseJunit4Test;
import com.liucan.pojo.Country;
import com.liucan.pojo.World;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/16
 * @brief
 */
public class Optional1Test extends BaseJunit4Test {
    @Autowired
    Optional1 optional1;

    private World world;

    @Before
    public void before() {
        super.before();

        world = new World();
        world.setId("1");
        Country country = new Country();
        //country.setName("222");
        country.setPeople(Long.valueOf("1234"));
        world.setCountry(country);
    }


    @Test
    public void testExample1() {
        optional1.example1();
    }

    @Test
    public void testExample2() {
        //正确使用Optional的姿势
        optional1.example2(world);
    }

    @Test
    public void testExample3() {
        //错误使用Optional的姿势-此方法和普通的判断没什么区别
        //不要用
        optional1.example3(world);
    }
}