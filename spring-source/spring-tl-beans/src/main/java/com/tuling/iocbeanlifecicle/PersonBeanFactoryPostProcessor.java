package com.tuling.iocbeanlifecicle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/9/22.
 */
@Component
public class PersonBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		RootBeanDefinition rootBeanDefinition =
				(RootBeanDefinition) beanFactory.getBeanDefinition("person");
		System.out.println(rootBeanDefinition.toString());
	}
}
