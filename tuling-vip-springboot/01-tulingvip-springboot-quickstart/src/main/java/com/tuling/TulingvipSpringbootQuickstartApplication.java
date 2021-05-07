package com.tuling;

import com.tuling.controller.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = Person.class)
public class TulingvipSpringbootQuickstartApplication {

	public static void main(String[] args) {
		SpringApplication.run(TulingvipSpringbootQuickstartApplication.class, args);
	}

}
