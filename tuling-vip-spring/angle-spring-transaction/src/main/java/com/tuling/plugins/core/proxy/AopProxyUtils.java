package com.tuling.plugins.core.proxy;

import org.springframework.aop.Advisor;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AopProxyUtils {

	/**
	 * 对方法应用advices增强
	 * 
	 * @param target
	 * @param method
	 * @param args
	 * @param matchAdvisors
	 * @param proxy
	 * @param applicationContext
	 * @return
	 * @throws Throwable
	 */
	public static Object applyAdvices(Object target, Method method, Object[] args, List<Advisor> matchAdvisors,
			Object proxy, ApplicationContext applicationContext) throws Throwable {
		// 这里要做什么？
		// 1、获取要对当前方法进行增强的advice
		List<Object> advices = AopProxyUtils.getShouldApplyAdvices(target.getClass(), method, matchAdvisors,
				applicationContext);


		// 2、如有增强的advice，责任链式增强执行
		if (CollectionUtils.isEmpty(advices)) {
			return method.invoke(target, args);
		} else {
			// 责任链式执行增强
			AopAdviceChainInvocation chain = new AopAdviceChainInvocation(proxy, target, method, args, advices);
			return chain.proceed();
		}
	}

	/**
	 * 获取与方法匹配的切面的advices
	 * 
	 * @param beanClass
	 * @param method
	 * @param matchAdvisors
	 * @param applicationContext
	 * @return
	 * @throws Exception
	 */
	public static List<Object> getShouldApplyAdvices(Class<?> beanClass, Method method, List<Advisor> matchAdvisors,
			ApplicationContext applicationContext) throws Throwable {
		List<Object> interceptors = new ArrayList<Object>();
		for(Advisor advisor:matchAdvisors) {
			interceptors.add(advisor.getAdvice());
		}
		return interceptors;
	}

}
