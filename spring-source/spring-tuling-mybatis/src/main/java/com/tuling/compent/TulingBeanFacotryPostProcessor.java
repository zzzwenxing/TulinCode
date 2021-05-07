package com.tuling.compent;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/9/11.
 */
//@Component
public class TulingBeanFacotryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		//去容器中获取我们的bean定义
		GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("deptMapper");
		//判断bean定义的class属性是不是我们的MapperFactoryBean类型的
		if(beanDefinition.getBeanClass().isAssignableFrom(MapperFactoryBean.class)) {
			//修改注入模型
			beanDefinition.setAutowireMode(0);
		}
	}
}
