package com.tuling.testautowired.autowiredinmethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by smlz on 2019/5/24.
 */
@Configuration
@ComponentScan(basePackages = "com.tuling.testautowired.autowiredinmethod")
public class MainConfig {

    @Bean
    public TulingAspect tulingAspect(@Autowired TulingLog tulingLog) {
        TulingAspect tulingAspect = new TulingAspect(tulingLog);
        return tulingAspect;
    }
}
