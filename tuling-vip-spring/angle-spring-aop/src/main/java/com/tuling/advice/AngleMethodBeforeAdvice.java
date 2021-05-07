package com.tuling.advice;

import com.tuling.enumaration.AdviceExecuteOrderEnum;
import com.tuling.joinpoint.interceptor.AngleMethodInterceptor;
import com.tuling.joinpoint.invocation.AngleMethodInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * 方法前置通知
 * Created by smlz on 2019/6/28.
 */
public class AngleMethodBeforeAdvice extends AngleAbstractAdvice implements AngleMethodInterceptor,Ordered {

    public AngleMethodBeforeAdvice(Method method,Class<?> aspectClass, ApplicationContext applicationContext) {
        super(method,aspectClass,applicationContext);
    }

    /**
     *
     * @param method aspect 中的方法
     * @param args 调用目标方法的参数
     * @param targetMethod 调用目标方法的所在类
     */
    public void before(Method method, Object[] args, Method targetMethod) throws Throwable{
        invokeAdvisorMethod(method,args,targetMethod);
    }

    /**
     * 调用下一个拦截器
     * @param invocation  方法调用对象
     * @return
     * @throws Throwable
     */
    public Object invoke(AngleMethodInvocation invocation) throws Throwable {
        before(this.getMethod(),invocation.getArguments(),invocation.getMethod());
        return invocation.proceed();
    }

    /**
     * 排序接口
     * @return
     */
    public int getOrder() {
        return AdviceExecuteOrderEnum.BEFORE_ADVICE.getExeOrder();
    }
}
