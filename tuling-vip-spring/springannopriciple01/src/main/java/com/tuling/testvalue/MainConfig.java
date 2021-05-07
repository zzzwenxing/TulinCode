package com.tuling.testvalue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:person.properties"})
public class MainConfig {

    @Bean
    public Person person() {
        return new Person();
    }
}