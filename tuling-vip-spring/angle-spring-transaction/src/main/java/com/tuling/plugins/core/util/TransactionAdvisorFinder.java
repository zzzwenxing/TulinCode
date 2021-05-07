package com.tuling.plugins.core.util;

import com.tuling.plugins.core.advisor.AngleTransactionAttributeSourceAdvisor;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by smlz on 2019/7/2.
 */
@Slf4j
public class TransactionAdvisorFinder {

    private AnnotationConfigApplicationContext applicationContext;


    private volatile String[] cachedAdvisorBeanNames;

    public TransactionAdvisorFinder(ApplicationContext applicationContext) {
        this.applicationContext = (AnnotationConfigApplicationContext) applicationContext;
    }

    public Object[] getTransactionInteceptor(Class<?> clazz) {
        //找到合适的额advisors
        List<Advisor> advisors = findEligibleAdvisors(clazz);
        return advisors.toArray();
    }
    
    public List<Advisor> findEligibleAdvisors(Class<?> clazz) {
        //找到候选的的advisor
        List<Advisor> candidateAdvisors = findCandidateAdvisors();
        //找到能用的advisor(看我们找出来的Advisor是否能找出@AngleTransaction注解)
        List<Advisor> eligibleAdvisors = findAdvisorsThatCanApply(candidateAdvisors, clazz);

        return eligibleAdvisors;
    }

    /**
     * 找到能用的Advisor
     * @param candidateAdvisors 候选的
     * @param clazz 当前执行的Bean
     * @return List<Advisor>
     */
    private List<Advisor> findAdvisorsThatCanApply(List<Advisor> candidateAdvisors, Class<?> clazz) {
        List<Advisor> eligibleAdvisors = new ArrayList<Advisor>();
        for(Advisor advisor:candidateAdvisors) {
            if(canApplyOnCurrentClass(clazz,advisor)) {
                eligibleAdvisors.add(advisor);
            }
        }
        return eligibleAdvisors;
    }

    /**
     * 判断真正能用的逻辑
     * @param clazz
     * @param advisor
     * @return
     */
    private boolean canApplyOnCurrentClass(Class<?> clazz, Advisor advisor) {
        AngleTransactionAttributeSourceAdvisor attributeSourceAdvisor = (AngleTransactionAttributeSourceAdvisor) advisor;
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method:methods){
                if(attributeSourceAdvisor.matches(method,clazz)){
                    return true;
                }
            }

        return false;
    }

    /**
     * 找到系统中能用的Advisor
     * @return List<Advisor>
     */
    private List<Advisor> findCandidateAdvisors() {

        String[] advisorNames = this.cachedAdvisorBeanNames;
        //没有缓存的
        if(advisorNames == null) {
            advisorNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(
                    this.applicationContext.getBeanFactory(), Advisor.class, true, false);
            this.cachedAdvisorBeanNames = advisorNames;
        }

        List<Advisor> advisors = new ArrayList<Advisor>();
        for(String name : advisorNames) {
            if(isEligibleBean(name)){
                //若当前正在创建Advisor类型的 就跳过
                if(applicationContext.getDefaultListableBeanFactory().isCurrentlyInCreation(name)) {
                }else {
                    advisors.add(applicationContext.getBean(name,Advisor.class));
                }
            }
        }
        return advisors;

    }

    private boolean isEligibleBean(String name) {
        if(applicationContext.containsBean(name) &&
                applicationContext.getBeanDefinition(name).getRole()== BeanDefinition.ROLE_INFRASTRUCTURE){
            return true;
        }
        return false;
    }


}
