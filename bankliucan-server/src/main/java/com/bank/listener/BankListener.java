package com.bank.listener;

import com.liucan.threadMgr.ThreadPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BankListener implements ServletContextListener {
    //上下文初始化
    @Override
    public void contextInitialized(ServletContextEvent var1) {
        ThreadPool.getInstance().start();
    }

    //上下文销毁
    @Override
    public void contextDestroyed(ServletContextEvent var1) {
        //结束任务
        ThreadPool.getInstance().stop();
    }
}
