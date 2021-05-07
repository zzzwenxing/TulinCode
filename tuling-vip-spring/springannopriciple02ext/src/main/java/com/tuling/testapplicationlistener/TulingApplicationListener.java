package com.tuling.testapplicationlistener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 创建一个监听器
 * Created by smlz on 2019/5/27.
 */
@Component
public class TulingApplicationListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("TulingApplicationListener 接受到了一个事件"+event);
    }
}
