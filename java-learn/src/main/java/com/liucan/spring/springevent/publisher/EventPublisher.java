package com.liucan.spring.springevent.publisher;

import com.liucan.spring.springevent.event.CustomEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author liucan
 * @date 2018/5/26
 * @brief 事件通知者
 */
public class EventPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    //spring 会在自动调用该函数设置ApplicationEventPublisher
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher){
        this.publisher = publisher;
    }

    public void publishCustomEvent() {
        publisher.publishEvent(new CustomEvent(this));
    }

    public void publishOtherEvent() {
        //可以是CustomEvent，也可以是其他类型的event
        //publisher.publishEvent();
    }
}
