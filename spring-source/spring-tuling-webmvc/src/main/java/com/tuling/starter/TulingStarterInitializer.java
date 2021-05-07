package com.tuling.starter;

import com.tuling.config.RootConfig;
import com.tuling.config.WebAppConfig;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 在tomcat源码中:
 *  org.apache.catalina.startup.ContextConfig#lifecycleEvent(org.apache.catalina.LifecycleEvent
 *  	org.apache.catalina.startup.ContextConfig#configureStart
 *          org.apache.catalina.startup.ContextConfig#webConfig
 *             org.apache.catalina.startup.ContextConfig#processServletContainerInitializers
 *
 *	 //通过spi的机制加载 classpath下ServletContainerInitializer
 *	 WebappServiceLoader<ServletContainerInitializer> loader = new WebappServiceLoader<>(context);
     detectedScis = loader.load(ServletContainerInitializer.class);
 *
 * Created by smlz on 2019/7/31.
 */

public class TulingStarterInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 方法实现说明:IOC 父容器的启动类
	 * @author:smlz
	 * @date:2019/7/31 22:12
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{RootConfig.class};
	}

	/**
	 * 方法实现说明 IOC子容器配置 web容器配置
	 * @author:smlz
	 * @date:2019/7/31 22:12
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{WebAppConfig.class};
	}

	/**
	 * 方法实现说明
	 * @author:smlz
	 * @return: 我们前端控制器DispatcherServlet的拦截路径
	 * @exception:
	 * @date:2019/7/31 22:16
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
}
