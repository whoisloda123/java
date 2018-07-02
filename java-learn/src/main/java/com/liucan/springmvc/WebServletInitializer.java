package com.liucan.springmvc;

import com.liucan.springmvc.config.AppConfig;
import com.liucan.springmvc.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author liucan
 * @date 2018/7/1
 * @brief 代替web.xml初始化DispatcherServlet
 *        1.基于XML的Spring配置方式：继承AbstractDispatcherServletInitializer
 *        2.基于Java配置的Spring应用，继承AbstractAnnotationConfigDispatcherServletInitializer
 */
public class WebServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final String SERVLET_NAME = "javalearn-dispatcher";

    /**
     * AppConfig配置
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * ServletConfig配置的定制化
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * url过滤器
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected String getServletName() {
        return SERVLET_NAME;
    }

    /**
     * 基类已经做了该做的工作了
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
}
