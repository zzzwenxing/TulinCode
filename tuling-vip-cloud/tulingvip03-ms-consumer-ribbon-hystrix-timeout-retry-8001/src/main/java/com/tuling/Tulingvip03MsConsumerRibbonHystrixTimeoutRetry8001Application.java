package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
public class Tulingvip03MsConsumerRibbonHystrixTimeoutRetry8001Application {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip03MsConsumerRibbonHystrixTimeoutRetry8001Application.class, args);
	}

}
