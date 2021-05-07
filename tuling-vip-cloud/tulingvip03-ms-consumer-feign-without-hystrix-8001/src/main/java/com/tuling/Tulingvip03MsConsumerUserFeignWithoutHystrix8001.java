package com.tuling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.tuling")
@Slf4j
@EnableCircuitBreaker
public class Tulingvip03MsConsumerUserFeignWithoutHystrix8001 {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip03MsConsumerUserFeignWithoutHystrix8001.class, args);
	}

}
