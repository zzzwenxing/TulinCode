package com.tuling.testautowired.autowired;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by smlz on 2019/5/24.
 */
@Configuration
@ComponentScan(basePackages = {"com.tuling.testautowired.autowired"})
public class TulingMainConfig {


    @Bean
    public TulingDao tulingDao2() {
        TulingDao tulingDao = new TulingDao();
        tulingDao.setFlag(2);
        return tulingDao;
    }

    //@Primary
    //@Bean
    public TulingDao tulingDao() {
        TulingDao tulingDao = new TulingDao();
        tulingDao.setFlag(1);
        return tulingDao;
    }

    //@Bean(autowire = Autowire.BY_TYPE)
    //@Bean(autowire = Autowire.BY_NAME)

    public TulingService tulingService() {
        return new TulingService();
    }
}
