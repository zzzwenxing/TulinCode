package com.tuling.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by smlz on 2019/5/21.
 */
@Configuration
@EnableWebMvc
public class TulingWebMvcConfig implements WebMvcConfigurer {

    /**
     * 请求试图映射
     * @param registry
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login.html");
    }

}
