package com.liucan.springmvc;

import com.liucan.springmvc.config.AppConfig;
import com.liucan.springmvc.config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * spring mvc :https://www.w3cschool.cn/spring_mvc_documentation_linesh_translation/?
 * @author liucan
 * @date 2018/7/1
 * @brief 代替web.xml初始化DispatcherServlet
 *        1.基于XML的Spring配置方式：继承AbstractDispatcherServletInitializer
 *        2.基于Java配置的Spring应用，继承AbstractAnnotationConfigDispatcherServletInitializer或者WebApplicationInitializer
 *          然后addListener,addServlet,addFilter
 */
public class WebServletInitializer implements WebApplicationInitializer {
    private static final String SERVLET_NAME = "javalearn-dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) {
        addListener(servletContext);
        addServlet(servletContext);
        addFilter(servletContext);
    }

    /**
     * ContextLoaderListener加载Application-context.xml里面的内容
     */
    private void addListener(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);

        servletContext.addListener(new ContextLoaderListener(rootContext));
    }

    private void addServlet(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfig.class);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet(SERVLET_NAME, new DispatcherServlet(dispatcherContext));
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");
    }

    /**
     * 字符编码
     */
    private void addFilter(ServletContext servletContext) {
        servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
    }

}
