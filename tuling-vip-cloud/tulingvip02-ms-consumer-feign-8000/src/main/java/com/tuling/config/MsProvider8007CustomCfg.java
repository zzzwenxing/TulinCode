package com.tuling.config;

import com.tuling.interceptor.TulingInterceptor;
import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * Created by smlz on 2019/4/10.
 */

public class MsProvider8007CustomCfg {

    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }



    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options feignRequestOptions() {
        return new Request.Options(30000, 30000);
    }

    @Bean
    public RequestInterceptor tulingInterceptor() {
        return new TulingInterceptor();
    }
}
