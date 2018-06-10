package com.liucan.springmvc.common;

import com.liucan.springmvc.common.exception.BusinessException;
import com.liucan.springmvc.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @author liucan
 * @date 2018/6/3
 * @brief 截获RestController异常
 */
@RestControllerAdvice
public class RestControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(RestControllerExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public CommonResponse handleBusinessException(BusinessException e) {
        LOG.error(e.getMessage(), e);
        return CommonResponse.error("业务系统错误");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse handleException(Exception e) {
        LOG.error(e.getMessage(), e);
        return CommonResponse.error("系统错误");
    }

    @ModelAttribute
    public void changeMode(Model mode) {
        mode.addAttribute("name", "liucan");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }
}
