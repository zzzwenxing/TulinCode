package com.tuling.dynamicproxy.anno;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by smlz on 2020/3/31.
 */
public class JDKDynamicProxy implements InvocationHandler {

	private Object target;

	private AngelProxyInterceptor angelProxyInterceptor;


	public JDKDynamicProxy(Object target) {
		this.target = target;
		angelProxyInterceptor = new AngelProxyInterceptor(target);
	}

	/**
	 * 获取被代理接口实例对象
	 * @param <T>
	 * @return
	 */
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object obj = angelProxyInterceptor.invoke(method,args);
		return obj;
	}


}
