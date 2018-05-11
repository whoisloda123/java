package com.liucan.test;

import com.liucan.test.JsonData.Country;
import com.liucan.test.JsonData.World;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class OptionalTest extends BaseTest {
    private void testOptional1() {
        //参考资料http://www.importnew.com/26066.html
        //调用工厂方法创建Optional实例
        Optional<String> name = Optional.of("YanWei");

        /*opNullable*/
        Optional<String> empty = Optional.ofNullable(null);

        /*isPresent*/
        if (name.isPresent()) {
            String str = name.get();
        }

        /*get*/
//        try {
//            empty.get();
//        } catch (NoSuchElementException e) {
//            e.printStackTrace();
//        }

        /*orElse*/
        String str = name.orElse("There is no value pressent1");

        /*orElseGet*/
        str = name.orElseGet(String::new);
        str = name.orElseGet(() -> "There is no value pressent1");

        /*orElseThrow*/
        str = name.orElseThrow(() -> new IllegalArgumentException());

        //consumer
        name.ifPresent(value -> System.out.println("the lenth is:" + value.length()));

        //function
        Optional<Integer> len = name.map(String::length);

        //predicate
        Optional<String> len7 = name.filter(value -> value.length() < 7);

        /*flatMap*/
        name.flatMap((value) -> Optional.of(value.toUpperCase()));
    }

    private String testOptional2(World world) {
        return Optional.ofNullable(world)
                .map(World::getCountry)
                .map(Country::getName)
                .orElse("default");
    }

    private String testOptional3(World world) {
        String countryName = null;
        Optional<World> worldOptional = Optional.ofNullable(world);
        if (worldOptional.isPresent()) {
            Country country = worldOptional.get().getCountry();
            Optional<Country> countryOptional = Optional.ofNullable(country);
            if (countryOptional.isPresent()) {
                countryName = countryOptional.get().getName();
            }
        }
        return countryName;
    }

    @Override
    public void testAll() {
        //参考资料：http://www.importnew.com/26066.html
        super.testAll();
        testOptional1();

        World world = new World();
        world.setId("1");
        Country country = new Country();
        //country.setName("222");
        country.setPeople(Long.valueOf("1234"));
        world.setCountry(country);

        //正确使用Optional的姿势
        String name = testOptional2(world);

        //错误使用Optional的姿势-此方法和普通的判断没什么区别
        //不要用
        name = testOptional3(world);
    }
}
