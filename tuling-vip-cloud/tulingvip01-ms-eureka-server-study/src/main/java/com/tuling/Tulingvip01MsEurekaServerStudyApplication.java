package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Tulingvip01MsEurekaServerStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip01MsEurekaServerStudyApplication.class, args);
	}

}
