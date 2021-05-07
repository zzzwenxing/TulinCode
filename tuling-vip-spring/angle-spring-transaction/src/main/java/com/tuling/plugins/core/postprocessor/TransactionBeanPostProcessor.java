package com.tuling.plugins.core.postprocessor;

import com.tuling.plugins.core.proxy.AngleDefaultAopProxyFactory;
import com.tuling.plugins.core.util.TransactionAdvisorFinder;
import org.springframework.aop.Advisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by smlz on 2019/7/2.
 */
public class TransactionBeanPostProcessor implements BeanPostProcessor,ApplicationContextAware {

    private AnnotationConfigApplicationContext applicationContext;

    private TransactionAdvisorFinder transactionAdvisorFinder;


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        List<Advisor> advisors = transactionAdvisorFinder.findEligibleAdvisors(bean.getClass());
        if(!advisors.isEmpty()) {
            try {
                bean = this.createProxy(bean,beanName,advisors,applicationContext);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return bean;
    }

    private Object createProxy(Object bean, String beanName, List<Advisor> matchAdvisors, AnnotationConfigApplicationContext applicationContext) throws Throwable {
        // 通过AopProxyFactory工厂去完成选择、和创建代理对象的工作。
        return new AngleDefaultAopProxyFactory().createAopProxy(bean,beanName,matchAdvisors, this.applicationContext).getProxy();
    }




    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (AnnotationConfigApplicationContext) applicationContext;
        transactionAdvisorFinder = new TransactionAdvisorFinder(applicationContext);
    }
}
