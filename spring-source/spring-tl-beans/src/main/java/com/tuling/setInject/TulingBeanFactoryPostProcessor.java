package com.tuling.setInject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/8/23.
 */
@Component
public class TulingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition rootBeanDefinition =
				(GenericBeanDefinition) beanFactory.getBeanDefinition("instA");

		//rootBeanDefinition.setBeanClass(InstD.class);

		rootBeanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		//rootBeanDefinition.setBeanClass(InstD.class);
		//rootBeanDefinition.setLazyInit(true);

		GenericBeanDefinition genericBeanDefinition =
				(GenericBeanDefinition) beanFactory.getBeanDefinition("person");
		ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
		constructorArgumentValues.addIndexedArgumentValue(0,"smlz");
		genericBeanDefinition.setConstructorArgumentValues(constructorArgumentValues);
	}
}
