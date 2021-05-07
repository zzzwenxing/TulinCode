package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Tulingspc01SpringbootWarApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Tulingspc01SpringbootWarApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Tulingspc01SpringbootWarApplication.class);
	}



}
