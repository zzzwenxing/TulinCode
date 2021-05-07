package com.tuling.setInject;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by smlz on 2019/8/23.
 */
public class TulingImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(InstD.class);

		registry.registerBeanDefinition("instD",rootBeanDefinition);
	}
}
