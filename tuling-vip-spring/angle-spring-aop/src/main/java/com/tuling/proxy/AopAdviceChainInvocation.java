package com.tuling.proxy;



import com.tuling.joinpoint.interceptor.AngleMethodInterceptor;
import com.tuling.joinpoint.invocation.AngleMethodInvocation;

import java.lang.reflect.Method;
import java.util.List;

public class AopAdviceChainInvocation implements AngleMethodInvocation {

	private Object proxy;
	private Object target;
	private Method method;
	private Object[] args;
	private List<Object> advices;


	public AopAdviceChainInvocation(Object proxy, Object target, Method method, Object[] args, List<Object> advices) {
		super();
		this.proxy = proxy;
		this.target = target;
		this.method = method;
		this.args = args;
		this.advices = advices;
	}

	// 责任链执行记录索引号
	private int currentInterceptorIndex = -1;

	public Object proceed() throws Throwable {
		//调用目标对象
		if (this.currentInterceptorIndex == this.advices.size() - 1) {
			return method.invoke(target,args);
		}

		Object interceptorOrInterceptionAdvice =this.advices.get(++this.currentInterceptorIndex);
		return ((AngleMethodInterceptor)interceptorOrInterceptionAdvice).invoke(this);
	}

	public Method getMethod() {
		return this.method;
	}

	public Object[] getArguments() {
		return args;
	}
}
