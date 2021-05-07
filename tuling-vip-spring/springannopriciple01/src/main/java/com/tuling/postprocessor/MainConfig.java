package com.tuling.postprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by smlz on 2019/6/28.
 */
@Configuration
@ComponentScan(basePackages = "com.tuling.postprocessor")
public class MainConfig {

    @Bean(initMethod = "init")
    public Compent compent() {
        return new Compent();
    }
}
