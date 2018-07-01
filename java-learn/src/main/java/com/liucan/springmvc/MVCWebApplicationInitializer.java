package com.liucan.springmvc;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @author liucan
 * @date 2018/7/1
 * @brief 代替web.xml初始化DispatcherServlet
 */
public class MVCWebApplicationInitializer implements WebApplicationInitializer {
    private static final String SERVLET_NAME = "javalearn-dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) {
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("classpath:spring/*.xml");

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(SERVLET_NAME, new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
