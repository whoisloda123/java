package com.liucan.json;

import com.alibaba.fastjson.JSON;
import com.liucan.pojo.Country;
import com.liucan.pojo.World;
import org.springframework.stereotype.Component;

@Component
public class Json {
    public void example() {
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
