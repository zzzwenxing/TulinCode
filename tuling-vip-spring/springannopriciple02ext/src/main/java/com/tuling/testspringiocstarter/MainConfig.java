package com.tuling.testspringiocstarter;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by smlz on 2019/6/12.
 */
@Configuration
@Import(value = {TulingService.class})
@ComponentScan(basePackages = "com.tuling.testspringiocstarter")
public class MainConfig {


    @Bean
    public TulingDataSource tulingDataSource() {
        return new TulingDataSource();
    }



}
