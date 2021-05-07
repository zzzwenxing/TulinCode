package com.tuling.spring;/**
 * Created by Administrator on 2018/8/26.
 */

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/26
 **/
public class LubanString implements BeanFactoryAware {
    private BeanFactory beanFactory;

    public void sayhello() {
        beanFactory.getBean(Hi.class);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
