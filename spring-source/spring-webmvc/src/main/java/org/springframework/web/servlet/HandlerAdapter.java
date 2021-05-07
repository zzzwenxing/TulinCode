/*
 * Copyright 2002-2013 the original author or authors.
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

package org.springframework.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:处理器适配器 应用到的设计模式:模版方法,适配器模式
* @author: smlz
* @createDate: 2019/8/7 16:40
* @version: 1.0
 *
 *HandlerAdapter
 *    ---AbstractHandlerMethodAdapter
 *    	 ---RequestMappingHandlerAdapter
 *    ---SimpleServletHandlerAdapter
 *    ---HttpRequestHandlerAdapter
 *    ---SimpleControllerHandlerAdapter
 *
*/
public interface HandlerAdapter {

	/**
	 * 方法实现说明:判断当前的HandlerAdapter是否支持当前的handler
	 * @author:smlz
	 * @param handler
	 * @return:
	 * @exception:
	 * @date:2019/8/7 17:20
	 */
	boolean supports(Object handler);

	/**
	 * 方法实现说明:处理我们的请求 返回ModelAndView
	 * @author:smlz
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param handler 处理器对象
	 * @return: ModelAndView
	 * @exception:
	 * @date:2019/8/7 17:21
	 */
	@Nullable
	ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

	/**
	 * Same contract as for HttpServlet's {@code getLastModified} method.
	 * Can simply return -1 if there's no support in the handler class.
	 * @param request current HTTP request
	 * @param handler handler to use
	 * @return the lastModified value for the given handler
	 * @see javax.servlet.http.HttpServlet#getLastModified
	 * @see org.springframework.web.servlet.mvc.LastModified#getLastModified
	 */
	long getLastModified(HttpServletRequest request, Object handler);

}
