package com.tuling.dynamicproxy.intf;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2020/3/31.
 */
public class MainClass {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(MainCofig.class);
		UserInfoMapper userInfoMapper = (UserInfoMapper) context.getBean("userInfoMapper");
		System.out.println("UserInfoMapper的类型:"+userInfoMapper.getClass());
		userInfoMapper.UserInfo(1);

	}
}
