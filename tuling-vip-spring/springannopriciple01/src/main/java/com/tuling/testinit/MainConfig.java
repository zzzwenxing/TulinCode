package com.tuling.testinit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by smlz on 2019/6/7.
 */
@Configuration
public class MainConfig {

    @Bean
    public Person person() {
        return new Person();
    }
}
