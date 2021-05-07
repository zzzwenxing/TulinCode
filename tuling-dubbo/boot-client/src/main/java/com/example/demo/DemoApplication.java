package com.example.demo;

import com.tuling.client.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableDubbo
@SpringBootApplication
public class DemoApplication {
    @Reference
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args).close();
    }


    @Bean
    public ApplicationRunner getBean() {
        return args -> {
            System.out.println(userService.getUser(1));
        };
    }
}
