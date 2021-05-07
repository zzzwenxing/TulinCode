package com.tuling.plugins.core.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 事务拦截器对象
 * Created by smlz on 2019/7/2.
 */
public class AngleTransactionInterceptor extends AbstractAngleTransactionInterceptor implements MethodInterceptor {

    public Object invoke(final MethodInvocation invocation) throws Throwable {
        Class<?> targetClass = invocation.getThis().getClass();
        return invokeWithinTransaction(invocation.getMethod(),targetClass ,new InvocationCallback() {

            public Object proceedWithInvocation() throws Throwable {
                return invocation.proceed();
            }
        });
    }
}
