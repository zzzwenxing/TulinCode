package com.tuling.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡配置类
 * Created by smlz on 2019/4/8.
 */
@Configuration
public class TulingLbCfg {

    @Bean
    public IRule tulingRandomRule() {
        return new TulingRandomRule();
    }

}
