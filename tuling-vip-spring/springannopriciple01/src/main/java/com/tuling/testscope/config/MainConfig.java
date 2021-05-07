package com.tuling.testscope.config;

import com.tuling.testscope.compent.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * Created by smlz on 2019/5/20.
 */
@Configuration
public class MainConfig {

    /**
     * 配置的bean 默认是单实例的
     * @return
     */
    @Bean
    @Lazy
    @Scope(scopeName = "prototype")
    public Person person() {
        return new Person();
    }
}
