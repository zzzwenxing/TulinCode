package com.tuling.advice;

import com.tuling.enumaration.AdviceExecuteOrderEnum;
import com.tuling.joinpoint.invocation.AngleMethodInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * 后置通知
 * Created by smlz on 2019/6/28.
 */
public class AngleMethodAfterAdvice extends AngleAbstractAdvice implements Ordered {


    public AngleMethodAfterAdvice(Method method, Class<?> aspectClass, ApplicationContext applicationContext) {
       super(method,aspectClass,applicationContext);
    }

    /**
     *
     * @param method 调用的目标方法
     * @param args 调用目标方法的参数
     */
    public  void after(Method method, Object[] args,Method targetMethod) throws Throwable{
        invokeAdvisorMethod(method,args,targetMethod);
    }

    /**
     * 调用下一个拦截器
     * @param invocation  方法调用对象
     * @return
     * @throws Throwable
     */
    public Object invoke(AngleMethodInvocation invocation) throws Throwable {

        try {
            return invocation.proceed();
        } finally {
            //后置通知被finally包裹 所以总是会执行
            after(this.getMethod(),invocation.getArguments(),invocation.getMethod());
        }
    }

    /**
     * 用于实现排序接口
     * @return
     */
    public int getOrder() {
        return AdviceExecuteOrderEnum.AFTER_ADVICE.getExeOrder();
    }
}
