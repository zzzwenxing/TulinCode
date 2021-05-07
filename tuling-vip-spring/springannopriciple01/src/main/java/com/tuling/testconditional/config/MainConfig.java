package com.tuling.testconditional.config;

import com.tuling.testconditional.compent.TulingAspect;
import com.tuling.testconditional.compent.TulingLog;
import com.tuling.testconditional.condition.TulingCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * Created by smlz on 2019/5/20.
 */
public class MainConfig {

//    @Bean
    public TulingAspect tulingAspect() {
        return new TulingAspect();
    }

    @Bean
    @Conditional(value = TulingCondition.class)
    public TulingLog tulingLog() {
        return new TulingLog();
    }
}
