package com.tuling.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by smlz on 2019/3/27.
 */
@Configuration
public class MainConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    /**
     * 随机算法的负载均衡
     * 默认是轮询算法
     * @return
     */
/*    @Bean
    public IRule TulingRule() {
        return new RandomRule();
    }*/
}
