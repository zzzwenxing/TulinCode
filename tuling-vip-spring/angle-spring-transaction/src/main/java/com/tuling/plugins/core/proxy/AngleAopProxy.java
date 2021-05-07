package com.tuling.plugins.core.proxy;

/**
 * aop代理接口
 * Created by smlz on 2019/6/27.
 */
public interface AngleAopProxy {

    /**
     * 获取代理对象
     * @return
     */
    Object getProxy();

    /**
     * 获取代理对象
     * @return
     */
    Object getProxy(ClassLoader classLoader);
}
