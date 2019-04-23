package com.liucan.springmvc.controller;

import com.liucan.mybatis.dao.UserInfoMapper;
import com.liucan.mybatis.mode.UserInfo;
import com.liucan.mybatis.mode.UserInfoExample;
import com.liucan.pojo.Person;
import com.liucan.pojo.Student;
import com.liucan.springmvc.common.response.CommonResponse;
import com.liucan.springmvc.common.validtor.PersonValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("liucan")
public class MyController {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("index")
    public String test(Model model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        //返回逻辑视图的名称，xml配置的ViewResolver会定义根据视图名称查找对于的视图
        return "index";
    }

    //form表单
    @GetMapping("student")
    public ModelAndView student() {
        return new ModelAndView("form/student", "command", new Student());
    }

    //form表单
    @GetMapping("person")
    public ModelAndView person() {
        return new ModelAndView("form/person", "command", new Person());
    }

    @GetMapping("redirectIndex")
    public String index() {
        return "redirect/redirectIndex";
    }

    //重定向，就是在controller的方法间重定向
    @GetMapping("redirect")
    public String redirect() {
        return "redirect:finalPage";
    }

    @GetMapping("finalPage")
    public String finalPage() {
        return "redirect/final";
    }

    @GetMapping("find_user")
    @ResponseBody
    public CommonResponse queryUser(@RequestParam("user_id") Integer userId) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<UserInfo> list = userInfoMapper.selectByExample(userInfoExample);
        return CommonResponse.ok(list.get(0));
    }

    @GetMapping("upload")
    public String upload() {
        return "form/upload";
    }

    /**
     * 1.@ModelAttribute 放在函数上面，在control里面的所有的requestMapping之前都会执行
     * 通常被用来填充一些公共需要的属性或数据
     * 2.@ModelAttribute放到函数参数里面，该方法参数的值将由model中取得。在model中存在以后，
     * 请求中所有名称匹配的参数都会填充到该参数中。被称为数据绑定，一个非常有用的特性，
     * 节约了你每次都需要手动从表格数据中转换这些字段数据的时间
     */
    @ModelAttribute("num")
    public int addAccount() {
        return 1;
    }

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("num1", 1);
    }

    /**
     * 使用javax的valid校验器
     */
    @PostMapping("addStudent")
    public String addStudent(@ModelAttribute("student") @Valid Student student,
                             BindingResult bindingResult,
                             ModelMap model) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error.getField() + ":").append(error.getDefaultMessage());
            }
            return sb.toString();
        } else {
            model.addAttribute("name", student.getName());
            model.addAttribute("age", student.getAge());
            model.addAttribute("id", student.getId());
            return "form/result";
        }
    }

    /**
     * 使用spring-jsr303的valid校验器
     */
    @PostMapping("addPerson")
    public String addPerson(@ModelAttribute("person") @Valid Person person,
                            BindingResult bindingResult,
                            ModelMap model) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error.getField() + ":").append(error.getDefaultMessage());
            }
            return sb.toString();
        } else {
            model.addAttribute("name", person.getName());
            model.addAttribute("age", person.getAge());
            return "form/result";
        }
    }

    @InitBinder //在该control里面所有RequestMapping之前都会执行
    public void initBinder(WebDataBinder webDataBinder) {
        //添加PersonValidtor
        if (webDataBinder.getTarget() instanceof Person) {
            webDataBinder.addValidators(new PersonValidator());
        }
    }

}
