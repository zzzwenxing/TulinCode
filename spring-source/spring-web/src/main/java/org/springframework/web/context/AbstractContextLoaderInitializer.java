/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.lang.Nullable;
import org.springframework.web.WebApplicationInitializer;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:AbstractContextLoaderInitializer主要是用来创建我们的根容器，
 * 但是创建根容器的方法createRootApplicationContext()由子类进行实现AbstractAnnotationConfigDispatcherServletInitializer
* @author: smlz
* @createDate: 2019/7/31 21:09
* @version: 1.0
*/
public abstract class AbstractContextLoaderInitializer implements WebApplicationInitializer {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());


	/**
	 * 方法实现说明:容器启动，实例化好我们的感兴趣类AbstractContextLoaderInitializer的onStartup
	 * @author:smlz
	 * @param servletContext 应用上下文对象
	 * @date:2019/7/31 20:58
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//直接调用我们的registerContextLoaderListener()
		registerContextLoaderListener(servletContext);
	}

	/**
	 * 方法实现说明:注册我们上下文监听器对象
	 * @author:smlz
	 * @param servletContext
	 * @date:2019/7/31 20:59
	 */
	protected void registerContextLoaderListener(ServletContext servletContext) {
		/**
		 * 创建我们的根的上下文环境WebApplicationContext(AnnotationConfigWebApplicationContext)对象，但是该方在本类中没有被实现，留个了子类实现
		 * AbstractAnnotationConfigDispatcherServletInitializer实现
		 *
		 */
		WebApplicationContext rootAppContext = createRootApplicationContext();
		//创建的对象WebApplicationContext不为空
		if (rootAppContext != null) {
			/**
			 * <listener>
			 *       <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
			 *  </listener>
			 *
			 *  <context-param>
			 *      <param-name>contextConfigLocation</param-name>
			 *      <param-value>/WEB-INF/app-context.xml</param-value>
			 *   </context-param>
			 * 创建一个监听器对象
			 */
			ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
			/**
			 * 第一步:获取根应用的getRootApplicationContextInitializers()
			 * 第二步:把初始化器设置到监听器中
			 */
			listener.setContextInitializers(getRootApplicationContextInitializers());
			//把监听器加入到我们上下文中.
			servletContext.addListener(listener);
		}
		else {
			logger.debug("No ContextLoaderListener registered, as " +
					"createRootApplicationContext() did not return an application context");
		}
	}

	/**
	* @vlog: 高于生活，源于生活
	* @desc: 类的描述:抽象方法创建我们的根容器，但是但是由他的子类
	*AbstractAnnotationConfigDispatcherServletInitializer实现
	* @author: smlz
	* @createDate: 2019/7/31 21:11
	* @version: 1.0
	*/
	@Nullable
	protected abstract WebApplicationContext createRootApplicationContext();

	/**
	 * Specify application context initializers to be applied to the root application
	 * context that the {@code ContextLoaderListener} is being created with.
	 * @since 4.2
	 * @see #createRootApplicationContext()
	 * @see ContextLoaderListener#setContextInitializers
	 */
	@Nullable
	protected ApplicationContextInitializer<?>[] getRootApplicationContextInitializers() {
		return null;
	}

}
