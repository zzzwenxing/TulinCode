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

import java.lang.reflect.Method;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:方法调用对象接口  aop使用的实现类就是 ReflectiveMethodInvocation
* @author: smlz
* @createDate: 2019/7/30 16:01
* @version: 1.0
*/
public interface MethodInvocation extends Invocation {

	/**
	 * 方法实现说明
	 * @author:smlz
	 * @return:Method 当前的调用对象
	 * @exception: Method
	 * @date:2019/7/30 16:02
	 */
	Method getMethod();

}
