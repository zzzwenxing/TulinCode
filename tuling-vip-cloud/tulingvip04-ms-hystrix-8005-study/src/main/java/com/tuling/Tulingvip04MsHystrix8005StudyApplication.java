package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class Tulingvip04MsHystrix8005StudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip04MsHystrix8005StudyApplication.class, args);
	}

}
