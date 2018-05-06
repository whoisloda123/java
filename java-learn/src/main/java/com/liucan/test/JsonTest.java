package com.liucan.test;

import com.alibaba.fastjson.JSON;
import com.liucan.test.JsonData.Country;
import com.liucan.test.JsonData.World;
import org.springframework.stereotype.Component;

@Component
public class JsonTest extends BaseTest {
    @Override
    public void testAll() {
        super.testAll();
        Country country = new Country();
        country.setName("中国");
        country.setPeople(Long.valueOf("1234"));

        World world = new World();
        world.setCountry(country);
        world.setId("1");

        String json = JSON.toJSONString(world);

        World world1 = JSON.parseObject(json, World.class);
    }
}
