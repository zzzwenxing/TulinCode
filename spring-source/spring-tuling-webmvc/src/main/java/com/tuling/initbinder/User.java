package com.tuling.initbinder;

import java.util.Date;

/**
 * Created by smlz on 2019/8/12.
 */

public class User {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String lastName;

	private Integer age;

	private Date birthDate;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", lastName='" + lastName + '\'' +
				", age=" + age +
				", birthDate=" + birthDate +
				'}';
	}

	public User(Integer id, String lastName, Integer age, Date birthDate) {
		this.id = id;
		this.lastName = lastName;
		this.age = age;
		this.birthDate = birthDate;
	}
}
