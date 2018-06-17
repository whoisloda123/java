package com.liucan.springmvc.controller;

import com.liucan.mybatis.dao.UserInfoMapper;
import com.liucan.mybatis.mode.UserInfo;
import com.liucan.mybatis.mode.UserInfoExample;
import com.liucan.springmvc.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liucan
 * @date 2018/6/16
 * @brief @RestController = @Controller + @ResponseBody
 */
@RestController //一般用于返回页面
@RequestMapping("/liucan")
public class MyRestController {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping(value = "/find_user1")
    public CommonResponse queryUser(@RequestParam(value = "user_id", required = false) Integer userId) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<UserInfo> list = userInfoMapper.selectByExample(userInfoExample);
        return CommonResponse.ok(list.get(0));
    }

    @GetMapping(value = "/find_user1/{user_Id}/{register_time}")
    public CommonResponse queryUser1(@PathVariable("user_Id") Integer userId,
                                     @PathVariable("register_time")
                                     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                             LocalDateTime registerTime) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<UserInfo> list = userInfoMapper.selectByExample(userInfoExample);
        return CommonResponse.ok(list.get(0));
    }

    @PostMapping(value = "/add_user")
    public CommonResponse queryUser2(@RequestBody UserInfo userInfo) {
        return CommonResponse.ok(userInfo);
    }
}
