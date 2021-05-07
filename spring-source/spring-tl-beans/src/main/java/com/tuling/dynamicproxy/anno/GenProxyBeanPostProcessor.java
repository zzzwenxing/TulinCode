package com.tuling.dynamicproxy.anno;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by smlz on 2020/3/31.
 */
@Component
public class GenProxyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {


		if(bean instanceof UserInfoServiceImpl) {
			//强制转型为UserInfoServiceImpl
			UserInfoServiceImpl userInfoService = (UserInfoServiceImpl) bean;

			if(existsAnnotation(userInfoService.getClass())) {
				//需要代理
				System.out.println("需要代理的bean..."+beanName);

				IUserInfoService iUserInfoService = new JDKDynamicProxy(userInfoService).getProxy();

				return iUserInfoService;
			}
			//不需要代理
			return bean;
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
/*		if(bean instanceof UserInfoServiceImpl) {
			//强制转型为UserInfoServiceImpl
			UserInfoServiceImpl userInfoService = (UserInfoServiceImpl) bean;

			if(existsAnnotation(userInfoService.getClass())) {
				//需要代理
				System.out.println("需要代理的bean..."+beanName);

				IUserInfoService iUserInfoService = new JDKDynamicProxy(userInfoService).getProxy();

				return iUserInfoService;
			}
			//不需要代理
			return bean;
		}
		return bean;*/

		if(bean instanceof UserInfoServiceImpl) {
			UserInfoServiceImpl userInfoService = (UserInfoServiceImpl) bean;
			userInfoService.setUserFlag(false);
			return userInfoService;
		}
		return bean;
	}

	/**
	 * 判断注解是否存在
	 * @return
	 */
	private boolean existsAnnotation(Class<?> targetClass) {
		Method[] targetMethod = targetClass.getMethods();
		if(targetMethod!=null) {
			for (Method method :targetMethod) {
				AngleProxy angleProxy = method.getAnnotation(AngleProxy.class);
				if(angleProxy !=null) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
}
