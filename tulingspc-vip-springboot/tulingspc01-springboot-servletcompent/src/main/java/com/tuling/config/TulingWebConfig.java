package com.tuling.config;

import com.tuling.filter.TulingFilter;
import com.tuling.interceptors.TulingInterceptor;
import com.tuling.servlet.TulingServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by smlz on 2019/5/21.
 */
@Configuration
public class TulingWebConfig implements WebMvcConfigurer {

    @Autowired
    private TulingInterceptor tulingInterceptor;

    /**
     * 注册一个filter
     * @return
     */
    @Bean
    public FilterRegistrationBean<TulingFilter> filterFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TulingFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean tulingServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new TulingServlet(),"/tuling");
        return servletRegistrationBean;
    }

    /**
     * 注册拦截器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tulingInterceptor).addPathPatterns("/**").excludePathPatterns("/index.html","/");
    }
}
