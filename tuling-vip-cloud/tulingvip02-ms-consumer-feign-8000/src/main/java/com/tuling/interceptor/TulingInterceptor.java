package com.tuling.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Created by smlz on 2019/4/10.
 */
public class TulingInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("自定义拦截器");
        template.header("token","123456");
    }
}
