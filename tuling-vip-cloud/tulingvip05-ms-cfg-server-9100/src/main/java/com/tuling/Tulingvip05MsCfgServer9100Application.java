package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Tulingvip05MsCfgServer9100Application {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip05MsCfgServer9100Application.class, args);
	}

}
