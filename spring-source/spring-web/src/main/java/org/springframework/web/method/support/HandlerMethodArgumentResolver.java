/*
 * Copyright 2002-2014 the original author or authors.
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

package org.springframework.web.method.support;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;

/**
* @vlog: 高于生活，源于生活  应用到的设计模式(模版方法)
* @desc: 类的描述:参数解析器接口,用于解析方法的参数值
 *       HandlerMethodArgumentResolver
 *       	--AbstractNamedValueMethodArgumentResolver
 *       	  --PathVariableMethodArgumentResolver (用于解析@PathVariable注解)
 *       	  --RequestParamMethodArgumentResolver(用于解析@RequestParam注解的)
 *          --AbstractMessageConverterMethodArgumentResolver
 *            --RequestResponseBodyMethodProcessor(用于解析@RequestBody  @ResponseBody)
* @author: smlz
* @createDate: 2019/8/15 14:58
* @version: 1.0
*/
public interface HandlerMethodArgumentResolver {

	/**
	 * 方法实现说明:判断参数解析器是否支持解析该参数
	 * @author:smlz
	 * @param parameter
	 * @return: true|false
	 * @exception:
	 * @date:2019/8/15 15:33
	 */
	boolean supportsParameter(MethodParameter parameter);

	/**
	 * 方法实现说明:真正的解析参数
	 * @author:smlz
	 * @param mavContainer 当前请求的上下文对象
	 * @param webRequest 当前的请求
	 * @param binderFactory 数据绑定器工厂，用于创建数据绑定器
	 * @return:
	 * @exception:
	 * @date:2019/8/15 15:48
	 */
	@Nullable
	Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception;

}
