package com.tuling.config;

import com.tuling.intercept.GlobalTransactionIdIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by smlz on 2019/7/28.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalTransactionIdIntercept()).addPathPatterns("/**");
    }

}
