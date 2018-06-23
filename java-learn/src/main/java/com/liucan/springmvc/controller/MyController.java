package com.liucan.springmvc.controller;

import com.liucan.mybatis.dao.UserInfoMapper;
import com.liucan.mybatis.mode.UserInfo;
import com.liucan.mybatis.mode.UserInfoExample;
import com.liucan.pojo.Student;
import com.liucan.springmvc.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller //一般用于返回页面
@RequestMapping("/liucan")
public class MyController {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        //返回逻辑视图的名称，xml配置的ViewResolver会定义根据视图名称查找对于的视图
        return "index";
    }

    //form表单
    @GetMapping(value = "/student")
    public ModelAndView student() {
        return new ModelAndView("form/student", "command", new Student());
    }

    @GetMapping(value = "/redirectIndex")
    public String index() {
        return "redirect/redirectIndex";
    }

    //重定向，就是在controller的方法间重定向
    @GetMapping(value = "/redirect")
    public String redirect() {
        return "redirect:finalPage";
    }

    @GetMapping(value = "/finalPage")
    public String finalPage() {
        return "redirect/final";
    }

    @GetMapping(value = "/find_user")
    @ResponseBody
    public CommonResponse queryUser(@RequestParam("user_id") Integer userId) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<UserInfo> list = userInfoMapper.selectByExample(userInfoExample);
        return CommonResponse.ok(list.get(0));
    }

    /**
     * 1.@ModelAttribute 放在函数上面，在control里面的所有的requestMapping之前都会执行
     * 通常被用来填充一些公共需要的属性或数据
     * 2.@ModelAttribute放到函数参数里面，该方法参数的值将由model中取得。在model中存在以后，
     * 请求中所有名称匹配的参数都会填充到该参数中。被称为数据绑定，一个非常有用的特性，
     * 节约了你每次都需要手动从表格数据中转换这些字段数据的时间
     */
    @ModelAttribute("num")
    public int addAccount(@RequestParam int number) {
        return number;
    }

    @ModelAttribute
    public void populateModel(@RequestParam String number, Model model) {
        model.addAttribute("num1", number);
    }

    @PostMapping(value = "/addStudent")
    public String addStudent(@ModelAttribute("student") Student student,
                             ModelMap model) {
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());
        return "form/result";
    }
}
