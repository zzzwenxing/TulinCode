package com.tuling.testfactorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 通过实现factoryBean接口往容器中注册组件
 * Created by smlz on 2019/5/20.
 */
public class CarFactoryBean implements FactoryBean<Car> {

    @Override
    public Car getObject() throws Exception {

        return new Car();
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
