package com.tuling.proxy;


import com.tuling.advisor.AngleAdvisor;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by smlz on 2019/6/27.
 */
public class AngleDefaultAopProxyFactory implements AopProxyFactory  {



    public AngleAopProxy createAopProxy(Object bean, String beanName, List<AngleAdvisor> matchAdvisors, ApplicationContext applicationContext) throws Throwable {

        return new AngleJdkDynamicProxy(beanName, bean, matchAdvisors, applicationContext);
    }
}
