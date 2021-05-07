package com.tuling.testconfiuration.config;

import com.tuling.testconfiuration.compent.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by smlz on 2019/5/19.
 */
@Configuration
public class MainConfig {

    @Bean
    public Person person(){
        return new Person();
    }
}
