package com.tuling.plugins.config;

import com.tuling.plugins.core.advisor.AngleTransactionAttributeSourceAdvisor;
import com.tuling.plugins.core.interceptors.AngleTransactionInterceptor;
import com.tuling.plugins.core.source.AngleTransactionAttributeSource;
import com.tuling.plugins.core.source.AnnotationAngleTransactionAttributeSource;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Role;

/**
 * 事务配置类
 * Created by smlz on 2019/7/2.
 */
@Configuration
public class AngleTransactionConfiguration {


    @Bean
    public AngleTransactionAttributeSource angleTransactionAttributeSource() {
        return new AnnotationAngleTransactionAttributeSource();
    }

    @Bean
    public AngleTransactionInterceptor angleTransactionInterceptor() {
        AngleTransactionInterceptor angleTransactionInterceptor = new AngleTransactionInterceptor();
        angleTransactionInterceptor.setAngleTransactionAttributeSource(angleTransactionAttributeSource());
        return angleTransactionInterceptor;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public AngleTransactionAttributeSourceAdvisor angleTransactionAttributeSourceAdvisor() {
        AngleTransactionAttributeSourceAdvisor attributeSourceAdvisor = new AngleTransactionAttributeSourceAdvisor();
        //attributeSourceAdvisor.setAngleTransactionAttributeSource(angleTransactionAttributeSource());
        attributeSourceAdvisor.setAdvice(angleTransactionInterceptor());
        return attributeSourceAdvisor;
    }

}
