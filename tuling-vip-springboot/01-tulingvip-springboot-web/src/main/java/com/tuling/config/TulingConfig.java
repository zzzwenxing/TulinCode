package com.tuling.config;

import com.tuling.filter.TulingFilter;
import com.tuling.interceptors.TulingInterceptor;
import com.tuling.servlet.TulingServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by smlz on 2019/3/19.
 */
@Configuration
public class TulingConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TulingInterceptor tulingInterceptor;

    /**
     * 注册拦截器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tulingInterceptor).addPathPatterns("/**").excludePathPatterns("/index.html","/");
    }



    /**
     * 注册一个filter
     * @return
     */
/*    @Bean
    public FilterRegistrationBean tulingFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TulingFilter());
        filterRegistrationBean.addUrlPatterns("*//*");
        return filterRegistrationBean;
    }*/

    @Bean
    public ServletRegistrationBean tulingServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new TulingServlet(),"/tuling123");
        return servletRegistrationBean;
    }

    /**
     * 请求试图映射
     * @param registry
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("index.html");
    }

}
