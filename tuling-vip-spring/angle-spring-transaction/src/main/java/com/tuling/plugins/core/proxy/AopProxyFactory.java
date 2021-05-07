package com.tuling.plugins.core.proxy;


import org.springframework.aop.Advisor;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by smlz on 2019/6/27.
 */
public interface AopProxyFactory {

    AngleAopProxy createAopProxy(Object bean, String beanName, List<Advisor> matchAdvisors, ApplicationContext applicationContext)
            throws Throwable;
}
