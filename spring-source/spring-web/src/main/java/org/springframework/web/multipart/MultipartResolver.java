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

package org.springframework.web.multipart;

import javax.servlet.http.HttpServletRequest;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:用于处理SpringMvc文件上传下载的
 *       主要实现类:CommonsMultipartResolver
* @author: smlz
* @createDate: 2019/8/5 17:25
* @version: 1.0
*/
public interface MultipartResolver {

	/**
	 * 方法实现说明:判断我们的请求是不是  内容类型( Content-Type )为 multipart/*
	 * @author:smlz
	 * @param request 请求对象
	 * @return:
	 * @exception:
	 * @date:2019/8/5 17:26
	 */
	boolean isMultipart(HttpServletRequest request);

	/**
	 * 方法实现说明:把我们的HttpServletRequest 转为MultipartHttpServletRequest类型的请求
	 * @author:smlz
	 * @param request
	 * @return:
	 * @exception:
	 * @date:2019/8/5 17:29
	 */
	MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException;

	/**
	 * 方法实现说明:清理处理 multipart 产生的资源，例如临时文件
	 * @author:smlz
	 * @param request
	 * @return:
	 * @exception:
	 * @date:2019/8/5 17:30
	 */
	void cleanupMultipart(MultipartHttpServletRequest request);

}
