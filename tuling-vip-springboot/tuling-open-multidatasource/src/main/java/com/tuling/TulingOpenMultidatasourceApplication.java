package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class TulingOpenMultidatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TulingOpenMultidatasourceApplication.class, args);
	}

}
