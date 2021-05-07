/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.aspectj;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import org.springframework.aop.AfterAdvice;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:我们的后置通知具体实现类对象 用于来执行我们的后置通知方法
 *       本类的设计模式就是【适配器模式】
* @author: smlz
* @createDate: 2019/7/30 16:08
* @version: 1.0
*/
@SuppressWarnings("serial")
public class AspectJAfterAdvice extends AbstractAspectJAdvice
		implements MethodInterceptor, AfterAdvice, Serializable {

	public AspectJAfterAdvice(
			Method aspectJBeforeAdviceMethod, AspectJExpressionPointcut pointcut, AspectInstanceFactory aif) {

		super(aspectJBeforeAdviceMethod, pointcut, aif);
	}


	/**
	 * 方法实现说明
	 * @author:smlz
	 * @param mi:主要调用对象是ReflectiveMethodInvocation 会通过ReflectiveMethodInvocation.proceed()方法
	 *          来执行我们的拦截器链
	 * @return:
	 * @exception:
	 * @date:2019/7/30 16:06
	 */
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		try {
			//本拦截器是后置通知拦截器对象,执行下一个通知
			return mi.proceed();
		}
		finally {
			//后置通知的方法总是会被执行 因为是finally包裹的
			invokeAdviceMethod(getJoinPointMatch(), null, null);
		}
	}

	@Override
	public boolean isBeforeAdvice() {
		return false;
	}

	@Override
	public boolean isAfterAdvice() {
		return true;
	}

}
