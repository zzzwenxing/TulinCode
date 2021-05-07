/*
 * Copyright 2002-2017 the original author or authors.
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

package org.springframework.web.servlet.support;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * {@link org.springframework.web.WebApplicationInitializer WebApplicationInitializer}
 * to register a {@code DispatcherServlet} and use Java-based Spring configuration.
 *
 * <p>Implementations are required to implement:
 * <ul>
 * <li>{@link #getRootConfigClasses()} -- for "root" application context (non-web
 * infrastructure) configuration.
 * <li>{@link #getServletConfigClasses()} -- for {@code DispatcherServlet}
 * application context (Spring MVC infrastructure) configuration.
 * </ul>
 *
 * <p>If an application context hierarchy is not required, applications may
 * return all configuration via {@link #getRootConfigClasses()} and return
 * {@code null} from {@link #getServletConfigClasses()}.
 *
 * @author Arjen Poutsma
 * @author Chris Beams
 * @since 3.2
 */
public abstract class AbstractAnnotationConfigDispatcherServletInitializer
		extends AbstractDispatcherServletInitializer {

	/**
	 * 方法实现说明:真正的创建我们的根容器
	 * @author:smlz
	 * @return:
	 * @exception:
	 * @date:2019/7/31 22:05
	 */
	@Override
	@Nullable
	protected WebApplicationContext createRootApplicationContext() {
		//获取我们的IOC父容器的配置类
		Class<?>[] configClasses = getRootConfigClasses();
		if (!ObjectUtils.isEmpty(configClasses)) {
			//创建我们的根容器
			AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
			//把配置类加载到根容器中
			context.register(configClasses);
			return context;
		}
		else {
			return null;
		}
	}

	/**
	 * 方法实现说明:真正的创建SpringMvc子容器对象
	 * @author:smlz
	 * @return:   WebApplicationContext
	 * @exception:
	 * @date:2019/7/31 21:51
	 */
	@Override
	protected WebApplicationContext createServletApplicationContext() {
		//创建我们的注解版本的配置的web应用上下文对象
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		/**
		 *获取我们的配置 子容器的配置类,但是getServletConfigClasses留给我们自己去实现
		 */
		Class<?>[] configClasses = getServletConfigClasses();
		if (!ObjectUtils.isEmpty(configClasses)) {
			//把配置类注册到我们的子容器中
			context.register(configClasses);
		}
		return context;
	}

	/**
	 * Specify {@code @Configuration} and/or {@code @Component} classes for the
	 * {@linkplain #createRootApplicationContext() root application context}.
	 * @return the configuration for the root application context, or {@code null}
	 * if creation and registration of a root context is not desired
	 */
	@Nullable
	protected abstract Class<?>[] getRootConfigClasses();

	/**
	 * Specify {@code @Configuration} and/or {@code @Component} classes for the
	 * {@linkplain #createServletApplicationContext() Servlet application context}.
	 * @return the configuration for the Servlet application context, or
	 * {@code null} if all configuration is specified through root config classes.
	 */
	@Nullable
	protected abstract Class<?>[] getServletConfigClasses();

}
