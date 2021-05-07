package com.tuling.proxy;


import com.tuling.advice.AngleAdvice;
import com.tuling.advisor.AngleAdvisor;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by smlz on 2019/6/27.
 */
public interface AopProxyFactory {

    AngleAopProxy createAopProxy(Object bean, String beanName, List<AngleAdvisor> matchAdvisors, ApplicationContext applicationContext)
            throws Throwable;
}
