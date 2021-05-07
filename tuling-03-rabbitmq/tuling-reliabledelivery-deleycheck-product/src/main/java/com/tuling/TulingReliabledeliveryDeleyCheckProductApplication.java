package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableTransactionManagement
@RestController
public class TulingReliabledeliveryDeleyCheckProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(TulingReliabledeliveryDeleyCheckProductApplication.class, args);
	}


}
