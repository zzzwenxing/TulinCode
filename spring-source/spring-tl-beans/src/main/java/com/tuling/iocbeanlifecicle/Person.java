package com.tuling.iocbeanlifecicle;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by smlz on 2019/8/15.
 */
public class Person implements InitializingBean {

	private String name;

	private String sex;

	public String getName() {
		return name;
	}


	public void initPerson() {
		System.out.println("init方法");
	}

	@PostConstruct
	public void init() {
		System.out.println("PostConstruct init");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet ....方法");
	}

	public Person() {
		System.out.println("构造方法....");
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", sex='" + sex + '\'' +
				'}';
	}
}
