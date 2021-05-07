package com.tuling.config;

import com.tuling.bean.User;
import com.tuling.bean.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by smlz on 2019/6/26.
 */
@Configuration
public class WebFluxConfig {

    @Autowired
    private UserHandler userHandler;

    @Bean
    public RouterFunction<ServerResponse> findOneRouter() {
        return route(GET("/findOneByRouting"), req -> userHandler.findOne(req));

    }

    @Bean
    public RouterFunction<ServerResponse> findAllRouter() {
        return route(GET("/findAllByRouting"), req -> userHandler.findAll(req));

    }

}
