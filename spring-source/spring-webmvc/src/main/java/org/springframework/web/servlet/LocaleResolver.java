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

package org.springframework.web.servlet;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:处理国际化资源的接口:实现类如下
 *        AcceptHeaderLocaleResolver:基于URL级别处理的 糗事通过http://xxxxxxxx?locale=zh_CN  来切换我们的语言
 *        SessionLocaleResolver:基于session级别的
 *             <bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver" />
          CookieLocaleResolver:基于Cookie级别的CookieLocaleResolver
          <bean id="localeResolver" class="org.springframework.web.servlet.i18n.CookieLocaleResolver" />

 *
* @author: smlz
* @createDate: 2019/8/5 19:56
* @version: 1.0
*/
public interface LocaleResolver {

	/**
	 * 方法实现说明：从我们请求中获取出我们的请求头信息:Accept-Language
	 * @author:smlz
	 * @param request 请求对象
	 * @return:
	 * @exception:
	 * @date:2019/8/5 20:00
	 */
	Locale resolveLocale(HttpServletRequest request);

	/**
	 * 方法实现说明：设置请求所使用的语言
	 * @author:smlz
	 * @param request
	 * @param response
	 * @param locale
	 * @date:2019/8/5 20:02
	 */
	void setLocale(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Locale locale);

}
