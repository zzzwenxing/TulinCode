package com.tuling.compent;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by smlz on 2019/4/3.
 */
public class MyBeanPostProcessor implements BeanPostProcessor{

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof TulingCompent) {
            System.out.println("是我TulingCompent 的后置处理器方法调用的postProcessBeforeInitialization");
        }
        return bean;
    }



    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof TulingCompent) {
            System.out.println("是我TulingCompent后置处理器的 postProcessAfterInitialization");
        }
        return bean;
    }
}
