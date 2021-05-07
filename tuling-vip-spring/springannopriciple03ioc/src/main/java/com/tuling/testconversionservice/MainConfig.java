package com.tuling.testconversionservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by smlz on 2019/6/3.
 */
@Configuration
public class MainConfig {

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converterSet = new HashSet<Converter>();
        converterSet.add(new String2DateConversionService());
        factoryBean.setConverters(converterSet);
        return factoryBean;
    }
}
