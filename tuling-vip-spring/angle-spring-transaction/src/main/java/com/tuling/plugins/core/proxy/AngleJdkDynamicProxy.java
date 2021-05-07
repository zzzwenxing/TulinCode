package com.tuling.plugins.core.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by smlz on 2019/6/27.
 */
@Slf4j
public class AngleJdkDynamicProxy implements AngleAopProxy,InvocationHandler{

    private String beanName;
    private Object target;
    private List<Advisor> matchAdvisors;

    private ApplicationContext applicationContext;

    public AngleJdkDynamicProxy(String beanName, Object target, List<Advisor> matchAdvisors, ApplicationContext applicationContext) {
        super();
        this.beanName = beanName;
        this.target = target;
        this.matchAdvisors = matchAdvisors;
        this.applicationContext = applicationContext;
    }

    public Object getProxy() {
        return this.getProxy(target.getClass().getClassLoader());
    }


    public Object getProxy(ClassLoader classLoader) {
        if (log.isDebugEnabled()) {
            log.debug("为" + target + "创建代理。");
        }
        return Proxy.newProxyInstance(classLoader, target.getClass().getInterfaces(), this);
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return AopProxyUtils.applyAdvices(target, method, args, matchAdvisors, proxy, applicationContext);
    }
}
