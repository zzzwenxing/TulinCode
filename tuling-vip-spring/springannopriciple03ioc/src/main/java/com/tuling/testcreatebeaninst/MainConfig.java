package com.tuling.testcreatebeaninst;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

/**
 * Created by smlz on 2019/6/3.
 */
@Configuration
//@ComponentScan(basePackages = {"com.tuling.testcreatebeaninst"})
public class MainConfig {

    @Bean(autowire = Autowire.NO)
    public TulingAspect tulingAspect() {
        return new TulingAspect();
    }

    @Bean
    @Primary
    public TulingLog tulingLog() {
        return new TulingLog();
    }

    @Bean
    public TulingLog tulingLog2() {
        return new TulingLog();
    }

}
