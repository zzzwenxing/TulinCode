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

package org.springframework.web.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:加载上下文监听器,父类是ContextLoader
* @author: smlz
* @createDate: 2019/8/1 14:08
* @version: 1.0
*/
public class ContextLoaderListener extends ContextLoader implements ServletContextListener {

	/**
	 * 方法实现说明:上下文加载监听器的构造方法 给加载web.xml的配置版本的
	 * @author:smlz
		<listener>
			<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
		</listener>

		<context-param>
			<param-name>contextConfigLocation</param-name>
		<	param-value>/WEB-INF/app-context.xml</param-value>
		</context-param>
	 * @exception:
	 * @date:2019/8/1 14:00
	 */
	public ContextLoaderListener() {
	}

	/**
	 * 方法实现说明:给我们spring注解配置版本使用的构造器
	 *
	 * @author:smlz
	 * @param :context 上下文
	 * @date:2019/8/1 14:01
	 */
	public ContextLoaderListener(WebApplicationContext context) {
		super(context);
	}


	/**
	 * 初始化根容器的web上下文对象
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		initWebApplicationContext(event.getServletContext());
	}


	/**
	 * Close the root web application context.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		closeWebApplicationContext(event.getServletContext());
		ContextCleanupListener.cleanupAttributes(event.getServletContext());
	}

}
