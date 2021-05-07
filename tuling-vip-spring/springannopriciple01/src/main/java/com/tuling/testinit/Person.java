package com.tuling.testinit;


import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by smlz on 2019/6/7.
 */
public class Person implements InitializingBean {

    public Person() {
        System.out.println("我是构造方法");
    }

    @PostConstruct
    public void initMethod() {
        System.out.println("我是初始化方法");
    }

    @PreDestroy
    public void destory() {
        System.out.println("我是销毁方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我是初始化方法");
    }
}
