package com.liucan.stream;

import com.liucan.pojo.Country;
import com.liucan.pojo.World;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Optional1 {
    //参考资料：http://www.importnew.com/26066.html
    public void example1() {
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

    public String example2(World world) {
        return Optional.ofNullable(world)
                .map(World::getCountry)
                .map(Country::getName)
                .orElse("default");
    }

    public String example3(World world) {
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
}
