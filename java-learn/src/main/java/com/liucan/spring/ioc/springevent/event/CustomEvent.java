package com.liucan.spring.ioc.springevent.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author liucan
 * @date 2018/5/26
 * @brief 自定义spring事件，spring内置事件有ContextStartedEvent，ContextStoppedEvent等等
 */
public class CustomEvent extends ApplicationEvent {
    public CustomEvent(Object source) {
        super(source); //调用基类构造函数
    }

    public void doEvent() {
        System.out.println("Do My Custom Event");
    }
}
