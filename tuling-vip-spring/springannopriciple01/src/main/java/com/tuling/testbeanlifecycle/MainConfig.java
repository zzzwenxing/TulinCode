package com.tuling.testbeanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by smlz on 2019/5/24.
 */
@Configuration
@ComponentScan(basePackages = "com.tuling.testbeanlifecycle")
public class MainConfig {

    @Scope(value = "prototype")
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }

    @Bean(initMethod = "init")
    public TulingLog tulingLog() {
        return new TulingLog();
    }

    @Bean
    public TulingBeanPostProcessor tulingBeanPostProcessor() {
        return new TulingBeanPostProcessor();
    }
}
