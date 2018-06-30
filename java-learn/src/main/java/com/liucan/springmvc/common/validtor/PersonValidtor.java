package com.liucan.springmvc.common.validtor;

import com.liucan.pojo.Person;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author liucan
 * @date 2018/6/30
 * @brief Person检验器, 有2种方法
 * 1.在类的属性上面添加类似于@Min注解，如在Student类上面加类似于@Min，简单方便
 * 2.重写Validator接口，在validate里面进行自定义校验，可以自己添加比较复杂的校验
 */
public class PersonValidtor implements Validator {
    /**
     * 判断支持的JavaBean类型，此处支持Student类型
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    /**
     * 实现Validator中的validate接口进行校验
     */
    @Override
    public void validate(Object obj, Errors errors) {
        //把校验信息注册到Error的实现类里
        if (obj instanceof Person) {
            Person person = (Person) obj;
            if (StringUtils.isEmpty(person.getAddress())) {
                errors.rejectValue("address", null, "地址不能为空!!!!");
            }
            if (person.getAge() < 18) {
                errors.rejectValue("age", null, "年龄未满18岁");
            }
            //此处可以实现其他自定义比较复杂的的校验
        }
    }
}
