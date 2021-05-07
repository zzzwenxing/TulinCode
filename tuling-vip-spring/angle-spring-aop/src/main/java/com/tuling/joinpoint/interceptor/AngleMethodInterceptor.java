package com.tuling.joinpoint.interceptor;

import com.tuling.joinpoint.invocation.AngleMethodInvocation;

/**
 * 方法拦截器接口
 * Created by smlz on 2019/6/30.
 */
public interface AngleMethodInterceptor {

    /**
     * 拦截方法调用
     * @param invocation  方法调用对象
     * @return Object
     * @throws Throwable 抛出的异常
     */
    Object invoke(AngleMethodInvocation invocation) throws Throwable;
}
