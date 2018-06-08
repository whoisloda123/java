package com.liucan.springmvc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liucan
 * @date 2018/6/3
 * @brief 1.@ControllerAdvice + @ExceptionHandler 实现全局的 Controller 层的异常处理
 * 类似于aop，会捕获所有controller没有捕获的异常
 * 2.可以减少代码量，不用在controller里面每个方法都加上try catch
 * 3.@ControllerAdvice(annotations = RestController.class) 指向所有带有注解@RestController的控制器
 * 4.@ControllerAdvice("org.example.controllers") 指向所有指定包中的控制器
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    CommonResponse handleException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return CommonResponse.error(-1, "系统错误");
    }

    /**
     * 处理所有业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    CommonResponse handleBusinessException(BusinessException e) {
        LOGGER.error(e.getMessage(), e);
        return CommonResponse.error(-1, "系统错误");
    }
}
