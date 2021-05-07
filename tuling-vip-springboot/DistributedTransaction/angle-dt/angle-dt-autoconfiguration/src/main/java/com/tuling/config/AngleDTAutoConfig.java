package com.tuling.config;

import com.tuling.core.AngleGlobalTransactionManager;
import com.tuling.support.aspect.AngleConnectionAspect;
import com.tuling.support.aspect.AngleTransactionalAspect;
import com.tuling.support.holder.AngleDTProperties;
import com.tuling.support.marker.ConfigMarker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @vlog: 高于生活，源于生活
* @desc: 分布式事务配置类
* @author: smlz
* @createDate: 2019/7/26 22:20
* @version: 1.0
*/
@Configuration
@Slf4j
@EnableConfigurationProperties(AngleDTProperties.class)
@ConditionalOnBean(ConfigMarker.class)
public class AngleDTAutoConfig {

    @Bean
    public AngleConnectionAspect angleConnectionAspect() {
        log.info("加载AngleConnectionAspect切面到容器中");
        return new AngleConnectionAspect();
    }

    @Bean
    public AngleTransactionalAspect angleTransactionalAspect() {
        log.info("加载AngleTransactionalAspect切面到容器中");
        return new AngleTransactionalAspect();
    }

    @Bean
    public AngleGlobalTransactionManager angleGlobalTransactionManager() {
        log.info("加载AngleGlobalTransactionManager到容器中");
        return new AngleGlobalTransactionManager();
    }


}
