package com.liucan.springmvc.controller;

import com.liucan.mybatis.dao.UserInfoMapper;
import com.liucan.mybatis.mode.UserInfo;
import com.liucan.mybatis.mode.UserInfoExample;
import com.liucan.springmvc.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author liucan
 * @date 2018/6/16
 * @brief @RestController = @Controller + @ResponseBody
 */
@RestController //一般用于返回数据
@RequestMapping("/liucan")
public class MyRestController {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping(value = "/find_user1")
    public CommonResponse queryUser(@RequestHeader("Content-Type") String contentType,
                                    @RequestHeader HttpHeaders httpHeaders,
                                    @RequestHeader Map<String, String> maps,
                                    @CookieValue(value = "uid", required = false) Integer userId) {
        if (userId != null) {
            UserInfoExample userInfoExample = new UserInfoExample();
            userInfoExample.createCriteria().andUserIdEqualTo(userId);
            List<UserInfo> list = userInfoMapper.selectByExample(userInfoExample);
            if (!list.isEmpty()) {
                return CommonResponse.ok(list.get(0));
            } else {
                return CommonResponse.error("未查询到user");
            }
        } else {
            return CommonResponse.error("cookie有问题");
        }
    }

    @GetMapping(value = "/find_user1/{user_Id}/{register_time}")
    public CommonResponse queryUser1(@PathVariable("user_Id") Integer userId,
                                     @RequestParam("user_id") Integer uId,
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

    @PostMapping(value = "/find_user1/entity")
    public HttpEntity httpEntity(RequestEntity requestEntity) {
        //请求内容
        HttpHeaders requestHeaders = requestEntity.getHeaders();
        Object requestBody = requestEntity.getBody();
        List<String> myHeader = requestHeaders.get("myHeader");

        //返回内容
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("content-type1", "231");
        Object responseBody = CommonResponse.ok(requestBody);
        return new ResponseEntity<>(responseBody, responseHeaders, HttpStatus.OK);
    }

    /**
     * 上传文件
     */
    @PostMapping(value = "/upload_file")
    public CommonResponse uploadFile(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
        }
        return CommonResponse.ok();
    }
}
