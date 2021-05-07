package com.tuling.testalias;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by smlz on 2019/6/4.
 */
@Configuration
public class MainClass {

    @Bean(name = {"aliasBean","aliasBean2"})
    public AliasBean aliasBean() {
        return new AliasBean();
    }
}
