package com.tuling.config;

import com.tuling.condition.TulingAspect;
import com.tuling.condition.TulingConditional;
import com.tuling.condition.TulingLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by smlz on 2019/3/28.
 */
@Configuration
public class CondtionConfig {

    @Bean
    public TulingAspect tulingAspect() {
        return new TulingAspect();
    }

    @Conditional(value = TulingConditional.class)
    @Bean
    public TulingLog tulingLog() {
        return new TulingLog();
    }
}
