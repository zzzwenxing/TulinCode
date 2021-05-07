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

package org.springframework.web.servlet;

import java.util.Locale;

import org.springframework.lang.Nullable;


/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:用于解析我们的视图对象的
 *
* @author: smlz
* @createDate: 2019/8/15 17:19
* @version: 1.0
*    ViewResolver
 *     --AbstractCachingViewResolver
 *       --UrlBasedViewResolver
 *       	--InternalResourceViewResolver(解析jsp)
 *          --AbstractTemplateViewResolver
 *            --FreeMarkerViewResolver(解析freemarker)
 *       --XmlViewResolver(解析xml)
 *       --ResourceBundleViewResolver
 *     --BeanNameViewResolver(解析基于beanNmae)
 *     --ViewResolverComposite(综合解析)
*/
public interface ViewResolver {


	/**
	 * 方法实现说明:解析视图对象
	 * @author:smlz
	 * @param viewName 视图名称
	 * @param locale 国家语言代码
	 * @return: View 视图对象
	 * @exception:
	 * @date:2019/8/15 17:21
	 */
	@Nullable
	View resolveViewName(String viewName, Locale locale) throws Exception;

}
