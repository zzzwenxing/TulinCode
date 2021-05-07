/*
 * Copyright 2002-2016 the original author or authors.
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

package org.aopalliance.intercept;


/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:继承我们的拦截器 所以MethodInterceptor也是一个advice类型的,提供一个invoke()方法
* @author: smlz
* @createDate: 2019/7/30 15:57
* @version: 1.0
*/
@FunctionalInterface
public interface MethodInterceptor extends Interceptor {
	
	/**
	 * 方法实现说明
	 * @author:smlz
	 * @param invocation 主要实现类:ReflectiveMethodInvocation
	 * @return:
	 * @exception:
	 * @date:2019/7/30 16:04
	 */
	Object invoke(MethodInvocation invocation) throws Throwable;

}
