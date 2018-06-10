package com.liucan.springmvc.controller;

import com.liucan.mybatis.dao.UserInfoMapper;
import com.liucan.mybatis.mode.UserInfo;
import com.liucan.mybatis.mode.UserInfoExample;
import com.liucan.pojo.Student;
import com.liucan.springmvc.common.exception.BusinessException;
import com.liucan.springmvc.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
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

    @PostMapping(value = "/addStudent")
    public String addStudent(@ModelAttribute("SpringWeb") Student student,
                             ModelMap model) {
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());
        return "form/result";
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
        if (true) {
            throw new BusinessException("方式发送方");
        }
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<UserInfo> list = userInfoMapper.selectByExample(userInfoExample);
        return CommonResponse.ok(list.get(0));
    }
}
