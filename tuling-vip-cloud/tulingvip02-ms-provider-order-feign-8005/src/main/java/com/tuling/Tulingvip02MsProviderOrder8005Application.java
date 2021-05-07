package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Tulingvip02MsProviderOrder8005Application {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip02MsProviderOrder8005Application.class, args);
	}

}
