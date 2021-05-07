package com.tuling.iocbeanlifecicle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/8/15.
 */
public class MainStarter {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

		Person person = (Person) context.getBean("person");

		System.out.println("person:"+person);
	}
}
