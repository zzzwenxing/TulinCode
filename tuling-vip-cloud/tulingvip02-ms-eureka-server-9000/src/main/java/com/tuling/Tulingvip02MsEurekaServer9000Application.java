package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Tulingvip02MsEurekaServer9000Application {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip02MsEurekaServer9000Application.class, args);
	}

}
