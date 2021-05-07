/*
 * Copyright 2002-2017 the original author or authors.
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

package org.springframework.web.bind.support;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:用于创建我们的WebDataBinder web数据绑定器 【工厂模式】
 * 
* @author: smlz
* @createDate: 2019/8/12 20:03
* @version: 1.0
*/
public interface WebDataBinderFactory {

	/**
	 * 方法实现说明:工厂模式,创建我们的数据绑定器对象
	 * WebDataBinderFactory
	 *   InitBinderDataBinderFactory
	 *   ServletRequestDataBinderFactory
	 *   DefaultDataBinderFactory
	 * @author:smlz
	 * @param webRequest 请求对象
	 * @param target HandlerMethod
	 * @return: WebDataBinder
	 * @exception:
	 * @date:2019/8/12 20:03
	 */
	WebDataBinder createBinder(NativeWebRequest webRequest, @Nullable Object target, String objectName)
			throws Exception;

}
