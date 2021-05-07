package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.tuling")
public class Tulingvip02MsConsumerUser8000Application {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip02MsConsumerUser8000Application.class, args);
	}

}
