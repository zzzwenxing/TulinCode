package com.tuling;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by smlz on 2019/5/29.
 */
@Configuration
public class MainConfig {

    @Bean
    public TulingLog tulingLog() {
        return new TulingLog();
    }

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        PropertyEditorRegistrar[] propertyEditorRegistrars = new PropertyEditorRegistrar[]{new DatePropertyEditorRegister()};
        customEditorConfigurer.setPropertyEditorRegistrars(propertyEditorRegistrars);
        return customEditorConfigurer;
    }
}
