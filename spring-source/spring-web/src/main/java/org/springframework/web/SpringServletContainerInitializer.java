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

package org.springframework.web;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;



/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述: Spring应用一启动(tomcat启动的时候，就会去扫描当前应用下导入jar包的META-INF/services)
* 的javax.servlet.ServletContainerInitializer名称中的类容，该内容就是ServletContainerInitializer的实现类
 * 全类名路径，然后会调用该实现的 onStartup方法,并且我们可以在ServletContainerInitializer
 * 的实现类上标注@HandlesTypes,配置WebApplicationInitializer接口，那么所有WebApplicationInitializer
 * 类的实现类都会被传递到onStartup方法的入参中，然后判断传递进来的WebApplicationInitializer的实现不是接口或者
 * 不是抽象类，那么就会进行通过反射调用生成对象。
* @author: smlz
* @createDate: 2019/7/31 20:37
* @version: 1.0
*/
@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {


	/**
	 * 方法实现说明:容器启动的时候会调用该方法，并且传入@HandlesTypes(WebApplicationInitializer.class)
	 * 类型的所有子类作为入参.
	 * @author:smlz
	 * @param webAppInitializerClasses 感兴趣类的集合
	 * @param servletContext 我们应用的上下文对象
	 * @date:2019/7/31 20:46
	 */
	@Override
	public void onStartup(@Nullable Set<Class<?>> webAppInitializerClasses, ServletContext servletContext)
			throws ServletException {

		List<WebApplicationInitializer> initializers = new LinkedList<>();
		//传入的兴趣类webAppInitializerClasses的所有子类
		if (webAppInitializerClasses != null) {
			//进行循环敢兴趣的类
			for (Class<?> waiClass : webAppInitializerClasses) {
				//判断感兴趣的类不是接口，不是抽象类
				if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
						WebApplicationInitializer.class.isAssignableFrom(waiClass)) {
					try {
						//通过反射调用创感兴趣类的实例，然后加入到initializers
						initializers.add((WebApplicationInitializer)
								ReflectionUtils.accessibleConstructor(waiClass).newInstance());
					}
					catch (Throwable ex) {
						throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
					}
				}
			}
		}

		if (initializers.isEmpty()) {
			servletContext.log("No Spring WebApplicationInitializer types detected on classpath");
			return;
		}

		servletContext.log(initializers.size() + " Spring WebApplicationInitializers detected on classpath");
		//若我们的WebApplicationInitializer的实现类 实现了Orderd接口或者是标注了@Order注解,会进行排序
		AnnotationAwareOrderComparator.sort(initializers);

		//依次循环调用我们的感兴趣的类的实例的onStartup方法
		for (WebApplicationInitializer initializer : initializers) {
			initializer.onStartup(servletContext);
		}
	}

}
