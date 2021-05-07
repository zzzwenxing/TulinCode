package com.tuling.testbeanpostpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/6/2.
 */
@Component
public class TulingAspect implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void invokeTulingLogPrint() {
        TulingLog tulingLog = applicationContext.getBean(TulingLog.class);
        tulingLog.print();
    }
}
