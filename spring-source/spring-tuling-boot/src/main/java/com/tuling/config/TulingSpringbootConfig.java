package com.tuling.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 简单版本springboot的配置类
 * Created by smlz on 2019/8/16.
 */
@Configuration
@ComponentScan(basePackages = {"com.tuling"})
@EnableWebMvc
public class TulingSpringbootConfig implements WebMvcConfigurer {

	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}
}
