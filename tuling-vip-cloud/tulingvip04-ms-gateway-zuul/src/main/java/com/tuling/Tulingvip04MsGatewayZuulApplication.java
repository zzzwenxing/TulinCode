package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Tulingvip04MsGatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip04MsGatewayZuulApplication.class, args);
	}



}
