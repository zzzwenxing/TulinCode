package com.tuling.advice;

import com.tuling.enumaration.AdviceExecuteOrderEnum;
import com.tuling.joinpoint.invocation.AngleMethodInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * 返回通知
 * Created by smlz on 2019/6/28.
 */
public class AngleMethodAfterReturingAdvice extends AngleAbstractAdvice implements Ordered {

    public AngleMethodAfterReturingAdvice(Method method,Class<?> aspectClass, ApplicationContext applicationContext) {
        super(method,aspectClass,applicationContext);
    }

    /**
     * 实现该方法，返回
     *
     * @param returnValue
     *            返回值
     * @param method
     *            被增强的方法
     * @param args
     *            方法的参数
     * @param targetMethod 目标方法
     * @throws Throwable
     */
    void afterReturning(Object returnValue, Method method, Object[] args ,Method targetMethod) throws Throwable{
        invokeAdvisorMethod(method,args,targetMethod);
    }


    /**
     * 调用下一个拦截器
     * @param invocation  方法调用对象
     * @return
     * @throws Throwable
     */
    public Object invoke(AngleMethodInvocation invocation) throws Throwable {
        Object retVal =  invocation.proceed();
        afterReturning(retVal,this.getMethod(),invocation.getArguments(),invocation.getMethod());
        return retVal;
    }

    /**
     * 排序接口
     * @return
     */
    public int getOrder() {
        return AdviceExecuteOrderEnum.RETURING_ADVICE.getExeOrder();
    }
}
