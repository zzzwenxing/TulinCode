package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class Tulingvip05MsProviderOrder8002AutoRefreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tulingvip05MsProviderOrder8002AutoRefreshApplication.class, args);
	}

}
