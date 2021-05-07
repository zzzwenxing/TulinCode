package com.tuling.dynamicproxy.intf;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2020/3/31.
 */
@Component
public class TulingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {

		GenericBeanDefinition genericBeanDefinition =
				(GenericBeanDefinition) beanFactory.getBeanDefinition("userInfoMapper");

		String sourceClassName = genericBeanDefinition.getBeanClassName();

		genericBeanDefinition.setBeanClass(TulingMapperFactoryBean.class);

		System.out.println(genericBeanDefinition.getBeanClassName());

		ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
		constructorArgumentValues.addGenericArgumentValue(sourceClassName);
		genericBeanDefinition.setConstructorArgumentValues(constructorArgumentValues);
	}
}
