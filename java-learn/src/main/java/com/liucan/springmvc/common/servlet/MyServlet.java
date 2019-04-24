package com.liucan.springmvc.common.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义servlet
 * 参考：https://www.cnblogs.com/xdp-gacl/p/3760336.html
 * https://blog.csdn.net/qq_19782019/article/details/80292110
 * a.servlet api是为开发java web程序提供的api规范，只需要编写一个java类继承servlet接口，然后部署到web服务器中
 * b.spring mvc 就是一个servlet（DispatcherServlet）程序,tomcat是web服务器（servlet容器）
 * spring mvc就是围绕DispatcherServlet做的文章
 * c.servlet运行过程
 * Servlet程序是由WEB服务器调用，web服务器收到客户端的Servlet访问请求后：
 * ① Web服务器首先检查是否已经装载并创建了该Servlet的实例对象。如果是，则直接执行第④步，否则，执行第②步。
 * ② 装载并创建该Servlet的一个实例对象。
 * ③ 调用Servlet实例对象的init()方法。
 * ④ 创建一个用于封装HTTP请求消息的HttpServletRequest对象和一个代表HTTP响应消息的HttpServletResponse对象，
 * 然后调用Servlet的service()方法并将请求和响应对象作为参数传递进去。
 * ⑤ WEB应用程序被停止或重新启动之前，Servlet引擎将卸载Servlet，并在卸载之前调用Servlet的destroy()方法
 *
 * @author liucan
 * @version 19-4-24
 */
@WebServlet(name = "MyServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("收到post请求了");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("收到get请求了");
    }
}
