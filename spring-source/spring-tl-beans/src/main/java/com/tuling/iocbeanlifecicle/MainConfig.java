package com.tuling.iocbeanlifecicle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by smlz on 2019/8/15.
 */
@Configuration
@ComponentScan(basePackages = {"com.tuling.iocbeanlifecicle"})
public class MainConfig {

	@Bean(initMethod = "initPerson")
	public Person person() {
		Person person = new Person();
		person.setName("张三");
		person.setSex("男");
		return person;
	}
}
