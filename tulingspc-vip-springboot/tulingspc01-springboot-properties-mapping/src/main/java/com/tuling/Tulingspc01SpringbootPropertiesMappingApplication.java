package com.tuling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigurationProperties(Person.class)
@RestController
public class Tulingspc01SpringbootPropertiesMappingApplication {

	@Autowired
	private Person person;

	public static void main(String[] args) {
		SpringApplication.run(Tulingspc01SpringbootPropertiesMappingApplication.class, args);
	}

	@RequestMapping("/getPersonInfo")
	public Person getPersonInfo() {
		return person;
	}

}
