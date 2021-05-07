package com.tuling.testautowired.inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by smlz on 2019/5/24.
 */
@Configuration
@ComponentScan(basePackages = {"com.tuling.testautowired.inject"})
public class TulingMainConfig {

    @Primary
    @Bean
    public TulingDao tulingDao2() {
        TulingDao tulingDao = new TulingDao();
        tulingDao.setFlag(2);
        return tulingDao;
    }
}
