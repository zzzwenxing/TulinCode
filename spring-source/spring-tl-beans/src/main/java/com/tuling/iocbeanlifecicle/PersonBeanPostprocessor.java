package com.tuling.iocbeanlifecicle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/8/15.
 */
@Component
public class PersonBeanPostprocessor implements BeanPostProcessor {

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		if(bean instanceof Person) {
			Person person = (Person) bean;
			person.setSex("人妖");
			return person;
		}

		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}
