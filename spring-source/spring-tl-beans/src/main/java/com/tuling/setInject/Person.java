package com.tuling.setInject;

import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/8/25.
 */
@Component
public class Person {

	private String userName;

	private Integer age;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Person(String userName) {
		this.userName = userName;
		System.out.println("person有参构造器---------userName");
	}

	public Person() {
		System.out.println("person无参构造器");
	}

	public Person(Integer age) {
		this.age = age;
		System.out.println("person有参构造器---------age");

	}



}
