package com.tuling.config;

import com.tuling.interceptor.TulingInterceptor;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;


/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述 web子容器
* @author: smlz
* @createDate: 2019/7/31 20:22
* @version: 1.0
*/
@Configuration
@ComponentScan(basePackages = {"com.tuling"},includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {RestController.class, Controller.class})
},useDefaultFilters =false)
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer{

	/**
	 * 配置拦截器
	 * @return
	 */
	@Bean
	public TulingInterceptor tulingInterceptor() {
		return new TulingInterceptor();
	}

	/**
	 * 文件上传下载的组件
	 * @return
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(1024*1024*10);
		return multipartResolver;
	}

	/**
	 * 注册处理国际化资源的组件
	 * @return
	 */
/*	@Bean
	public AcceptHeaderLocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
		return acceptHeaderLocaleResolver;
	}*/

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tulingInterceptor()).addPathPatterns("/*");
	}

	/**
	 * 方法实现说明:配置试图解析器
	 * @author:smlz
	 * @exception:
	 * @date:2019/8/6 16:23
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");
		viewResolver.setPrefix("/WEB-INF/jsp/");
		return viewResolver;
	}



	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}

}
