package com.tuling.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.beans.PropertyDescriptor;

/**
 * 抽象代理创建器对象
 * Created by smlz on 2019/6/27.
 */
public abstract class AngleAbstractCreator implements BeanPostProcessor,InstantiationAwareBeanPostProcessor,ApplicationContextAware {

    private ApplicationContext applicationContext;


    /**
     * 实例化之后调用的方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    /**
     * 若不处理直接返回当前的bean,切记切记不要返回null
     * @param bean 当前正在创建的bean
     * @param beanName 当前正常创建的beanName
     * @return 处理过的bean
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 我们容器中需要使用ioc底层的beanFactory 所以我们实现了BeanFactoryAware接口
     * @param applicationContext bean工厂
     * @throws BeansException
     */


    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
