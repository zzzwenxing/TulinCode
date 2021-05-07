package com.tuling.config;

import com.tuling.compent.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by smlz on 2019/6/24.
 */
@Configuration
public class MainConfig {

    @Bean
    public Person person() {
        return new Person();
    }
}
