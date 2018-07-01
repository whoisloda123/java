package com.liucan.springmvc;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author liucan
 * @date 2018/7/1
 * @brief 代替web.xml初始化DispatcherServlet
 *        1.基于XML的Spring配置方式：继承AbstractDispatcherServletInitializer
 *        2.基于Java配置的Spring应用，继承AbstractAnnotationConfigDispatcherServletInitializer
 */
public class MVCServletInitializer extends AbstractDispatcherServletInitializer {
    private static final String SERVLET_NAME = "javalearn-dispatcher";

    /**
     * 基类已经做了该做的工作了
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }

    @Override
    protected String getServletName() {
        return SERVLET_NAME;
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        XmlWebApplicationContext cxt = new XmlWebApplicationContext();
        cxt.setConfigLocation("classpath:spring/*.xml");
        return cxt;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
