package com.liucan.springmvc.config;

import com.liucan.springmvc.common.interceptor.CommonInterceptor;
import com.liucan.springmvc.common.validtor.CommonValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author liucan
 * @date 2018/7/1
 * @brief sprig-mvc默认配置的定制化，本身有默认配置
 * Configuration:相当于xml里面的beans
 * EnableWebMvc:相当于xml里面的<mvc:annotation-driven/>，配置注解驱动
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 转换与格式化
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
    }

    /**
     * 验证器
     */
    @Override
    public Validator getValidator() {
        return new CommonValidator();
    }

    /**
     * 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/liucan/**");
    }

    /**
     * 视图控制器
     * 立即将一个请求转发（forwards）给一个视图
     * 当控制器除了将视图渲染到响应中外不需要执行任何逻辑时使用
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/liucan").setViewName("index");
    }

    /**
     * 视图解析器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        super.configureViewResolvers(registry);
    }

    /**
     * 路径匹配配置
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
    }

    /**
     * 消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }
}
