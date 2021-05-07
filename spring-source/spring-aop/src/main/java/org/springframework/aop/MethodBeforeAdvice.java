/*
 * Copyright 2002-2012 the original author or authors.
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

package org.springframework.aop;

import java.lang.reflect.Method;

import org.springframework.lang.Nullable;

/**
 * Advice invoked before a method is invoked. Such advices cannot
 * prevent the method call proceeding, unless they throw a Throwable.
 *
 * @see AfterReturningAdvice
 * @see ThrowsAdvice
 *
 * @author Rod Johnson
 */
/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述,方法前置通知接口,提供一个方法用于来执行我们aop中切面的方法
* @author: smlz
* @createDate: 2019/7/30 15:49
* @version: 1.0
*/
public interface MethodBeforeAdvice extends BeforeAdvice {

	/**
	 * Callback before a given method is invoked.
	 * @param method method being invoked
	 * @param args arguments to the method
	 * @param target target of the method invocation. May be {@code null}.
	 * @throws Throwable if this object wishes to abort the call.
	 * Any exception thrown will be returned to the caller if it's
	 * allowed by the method signature. Otherwise the exception
	 * will be wrapped as a runtime exception.
	 */
	/**
	 * 方法实现说明:执行我们的前置通知
	 * @author:smlz
	 * @param method 传入进来的方法对象
	 * @param args:入参
	 * @param target:目标对象
	 * @return: void
	 * @exception:
	 * @date:2019/7/30 15:51
	 */
	void before(Method method, Object[] args, @Nullable Object target) throws Throwable;

}
