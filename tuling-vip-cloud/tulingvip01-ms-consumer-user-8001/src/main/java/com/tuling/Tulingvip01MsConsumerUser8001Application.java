package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Tulingvip01MsConsumerUser8001Application {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip01MsConsumerUser8001Application.class, args);
	}

}
