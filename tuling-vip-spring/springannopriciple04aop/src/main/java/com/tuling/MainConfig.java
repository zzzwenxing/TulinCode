package com.tuling;

import org.springframework.context.annotation.*;

/**
 * Created by smlz on 2019/6/10.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableAspectJAutoProxy(exposeProxy = true)
public class MainConfig {

    @Bean
    public Calculate calculate() {
        return new TulingCalculate();
    }

    @Bean
    public TulingLogAspect tulingLogAspect() {
        return new TulingLogAspect();
    }
}
