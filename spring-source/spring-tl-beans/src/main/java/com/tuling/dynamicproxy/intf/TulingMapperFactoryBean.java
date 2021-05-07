package com.tuling.dynamicproxy.intf;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;


/**
 * Created by smlz on 2020/3/31.
 */
public class TulingMapperFactoryBean implements FactoryBean {

	private Class targetClass;

	public TulingMapperFactoryBean(Class targetClass) {
		this.targetClass = targetClass;
	}

	@Nullable
	@Override
	public UserInfoMapper getObject() throws Exception {
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("被调用的方法:{}"+method.getName()+"入参:"+ Arrays.asList(args));
				TulingSelect tulingSelect = method.getAnnotation(TulingSelect.class);
				String selectSql = tulingSelect.value();
				System.out.println("执行的业务selectSql:"+selectSql);
				return null;
			}
		};


		UserInfoMapper userInfoMapper = (UserInfoMapper) Proxy.newProxyInstance(targetClass.getClassLoader(),
				new Class<?>[] { UserInfoMapper.class },
				handler);
		return userInfoMapper;
	}

	@Nullable
	@Override
	public Class<?> getObjectType() {
		return targetClass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
