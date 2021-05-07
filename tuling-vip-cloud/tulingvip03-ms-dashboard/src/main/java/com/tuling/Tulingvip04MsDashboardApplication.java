package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 写注解
 */
@SpringBootApplication
@EnableHystrixDashboard
public class Tulingvip04MsDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip04MsDashboardApplication.class, args);
	}

}
