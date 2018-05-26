package com.liucan.spring.springevent.listener;

import com.liucan.spring.springevent.event.CustomEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author liucan
 * @date 2018/5/26
 * @brief 1.自定义事件Listener,spring会自己将其放入listener表，观察者模式
 *        2.ApplicationEventPublisher->publishEvent会自动根据其类型调用其Listener的onApplicationEvent
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        //do something
        event.doEvent();
    }
}
