package com.tuling.plugins.core.advisor;

import com.tuling.plugins.core.interceptors.AngleTransactionInterceptor;
import com.tuling.plugins.core.source.AngleTransactionAttributeSource;
import lombok.Data;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;

import java.lang.reflect.Method;

/**
 * Created by smlz on 2019/7/3.
 */
@Data
public class AngleTransactionAttributeSourceAdvisor implements Advisor {


    //private AngleTransactionAttributeSource angleTransactionAttributeSource;

    private Advice advice;

    public Advice getAdvice() {
        return this.advice;
    }

    public boolean isPerInstance() {
        return true;
    }

    public boolean matches(Method method,Class<?> targetClass){
        return ((AngleTransactionInterceptor)advice).getAngleTransactionAttributeSource().getTransactionAttribute(method,targetClass)!=null;
    }
}
