package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.tuling")
@EnableCircuitBreaker
public class Tulingvip03MsConsumerUserFeignWithHystrix8001 {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip03MsConsumerUserFeignWithHystrix8001.class, args);
	}

}
