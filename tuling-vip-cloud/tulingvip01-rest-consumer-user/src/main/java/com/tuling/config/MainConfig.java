package com.tuling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by smlz on 2019/3/26.
 */
@Configuration
public class MainConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new TulingRestTemplate();
    }
}
