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
* @vlog: 高于生活，源于生活
* @desc: 类的描述:返回通知接口,用于定义返回通知方法
* @author: smlz
* @createDate: 2019/7/30 16:15
* @version: 1.0
*/
public interface AfterReturningAdvice extends AfterAdvice {

	/**
	 * 方法实现说明:返回通知方法定义
	 * @author:smlz
	 * @param  returnValue 返回值
	 * @param method 方法对象
	 * @param args 参数
	 * @param target 目标参数
	 * @exception:Throwable
	 * @date:2019/7/30 16:16
	 */
	void afterReturning(@Nullable Object returnValue, Method method, Object[] args, @Nullable Object target) throws Throwable;

}
