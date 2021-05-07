package com.tuling.testbeanpostpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/5/31.
 */
@Component
public class TulingLogPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof TulingLog) {
            TulingLog tulingLog = (TulingLog) bean;
            tulingLog.setFlag(true);
            return tulingLog;
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
