package com.tuling.testbeanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器  在bean调用初始化方法前后进行调用
 * Created by smlz on 2019/5/24.
 */
//@Component
public class TulingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("TulingBeanPostProcessor...postProcessBeforeInitialization:"+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("TulingBeanPostProcessor...postProcessAfterInitialization:"+beanName);
        return bean;
    }
}
