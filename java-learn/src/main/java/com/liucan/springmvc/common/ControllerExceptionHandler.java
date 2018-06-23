package com.liucan.springmvc.common;

import com.liucan.springmvc.common.exception.BusinessException;
import com.liucan.springmvc.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author liucan
 * @date 2018/6/3
 * @brief
 *  1.@ControllerAdvice + @ExceptionHandler 实现全局的 Controller 层的异常处理
 *    类似于aop，会捕获所有controller没有捕获的异常
 *  2.可以减少代码量，不用在controller里面每个方法都加上try catch
 *  3.@ControllerAdvice(annotations = RestController.class) 指向所有带有注解@RestController的控制器
 *  4.@ControllerAdvice("org.example.controllers") 指向所有指定包中的控制器
 *  5.还有ModelAttribute（在所有@RequestMapping之前对mode的操作）和InitBinder
 *  6.@RestControllerAdvice = @ControllerAdvice + @ResponseBody 相当于 @RestController = @Controller + @ResponseBody
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Exception.class)
    CommonResponse handleException(Exception e) {
        LOG.error(e.getMessage(), e);
        return CommonResponse.error("系统错误");
    }

    /**
     * 处理所有业务异常
     */
    @ExceptionHandler(BusinessException.class)
    CommonResponse handleBusinessException(BusinessException e) {
        LOG.error(e.getMessage(), e);
        return CommonResponse.error("业务系统错误");
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model
     */
    @ModelAttribute
    public void changeModel(Model model) {
        model.addAttribute("author", "Jim");
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * 1.从请求参数、路径变量、请求头属性或者cookie中抽取出来的String类型的值，可能需要被转换成其所绑定的目标方法参数或字段的类型
     *  （比如，通过@ModelAttribute将请求参数绑定到方法参数上）。如果目标类型不是String，
     *  Spring会自动进行类型转换。所有的简单类型诸如int、long、Date都有内置的支持。如果想进一步定制这个转换过程，
     *  可以通过WebDataBinder
     * 2.用于完成由表单到JavaBean属性的绑定
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //定制日期的formatter
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }
}
