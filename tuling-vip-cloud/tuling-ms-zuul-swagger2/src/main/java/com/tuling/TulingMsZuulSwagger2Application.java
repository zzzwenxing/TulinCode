package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableZuulServer
public class TulingMsZuulSwagger2Application {

	public static void main(String[] args) {
		SpringApplication.run(TulingMsZuulSwagger2Application.class, args);
	}

}
