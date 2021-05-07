package com.tuling.core;


import com.busi.TulingLogAspect;
import com.tuling.advice.AngleAbstractAdvice;
import com.tuling.advice.AngleAdvice;
import com.tuling.advisor.AngleAdvisor;
import com.tuling.advisor.AngleAspectPointCutAdvisor;
import com.tuling.advisor.AnglePointcutAdvisor;
import com.tuling.proxy.AngleDefaultAopProxyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 查找切面 和创建代理对象功能都在该类实现
 * Created by smlz on 2019/6/27.
 */
@Slf4j
public class AngleAutoProxyCreator extends AngleAbstractCreator  {

    private AngleAspectAdvisorBuilder angleAspectAdvisorBuilder;


    /**
     * 查找容器中的切面信息
     * @param beanClass 当前正常创建bean的class对象
     * @param beanName 当前正在创建的beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        try {
            angleAspectAdvisorBuilder.transAdvice2Advisor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生存代理对象
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        List<AngleAdvisor> angleAdvices =  getMatchedAdvice(bean.getClass());
        if(bean.getClass().equals(TulingLogAspect.class)) {
            return bean;
        }
        if(!angleAdvices.isEmpty()) {
            //创建代理对象
            try {
                bean = this.createProxy(bean, beanName, angleAdvices);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return bean;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        //初始化切面构造器
        angleAspectAdvisorBuilder = new AngleAspectAdvisorBuilder();
        angleAspectAdvisorBuilder.setApplicationContext(applicationContext);
    }

    /**
     * 包装我们的bean对象
     * @param clazz 需要包装的bean的class
     * @return
     */
    public List<AngleAdvisor> getMatchedAdvice(Class clazz){
        if(angleAspectAdvisorBuilder.getAdvisorsCache().isEmpty()) {
            return null;
        }

        List<Method> methods =getAllMethodForClass(clazz);

        // 存放匹配的Advisor的list
        List<AngleAdvisor> matchAdvisors = new ArrayList<AngleAdvisor>();

        for(String aspectName : angleAspectAdvisorBuilder.getAdvisorsCache().keySet()) {
            List<AngleAdvisor> listMap = angleAspectAdvisorBuilder.getAdvisorsCache().get(aspectName);
            for(AngleAdvisor angleAdvisor:listMap) {
                if(((AngleAspectPointCutAdvisor)angleAdvisor).getAnglePointCut().matchsClass(clazz)){
                    matchAdvisors.add(angleAdvisor);
                }
            }
        }
        return matchAdvisors;
    }

    private List<Method> getAllMethodForClass(Class<?> beanClass) {
        List<Method> allMethods = new LinkedList<Method>();
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>(ClassUtils.getAllInterfacesForClassAsSet(beanClass));
        classes.add(beanClass);
        for (Class<?> clazz : classes) {
            Method[] methods = ReflectionUtils.getAllDeclaredMethods(clazz);
            for (Method m : methods) {
                allMethods.add(m);
            }
        }
        return allMethods;
    }

    private Object createProxy(Object bean, String beanName, List<AngleAdvisor> matchAdvisors) throws Throwable {
        // 通过AopProxyFactory工厂去完成选择、和创建代理对象的工作。
        return new AngleDefaultAopProxyFactory().createAopProxy(bean,beanName,matchAdvisors,getApplicationContext()).getProxy();
    }
}
