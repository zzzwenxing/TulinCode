package com.tuling.testmessage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.annotation.AliasFor;

import javax.annotation.Resource;

/**
 * Created by smlz on 2019/5/31.
 */
@Configuration
public class MainConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasenames("i18n/messages","i18n/messages_zh_CN");
        return resource;
    }
}
