package com.tuling.dynamicproxy.anno;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by smlz on 2020/3/31.
 */
public class AngelProxyInterceptor {

	private Object targetObj;

	public AngelProxyInterceptor(Object targetObj) {
		this.targetObj = targetObj;
	}

	public Object invoke(Method method,Object[] args ) throws InvocationTargetException, IllegalAccessException {
		System.out.println("执行方法之前");
		Object result = method.invoke(targetObj,args);
		System.out.println("执行目标方法之后");
		return result;
	}

}
