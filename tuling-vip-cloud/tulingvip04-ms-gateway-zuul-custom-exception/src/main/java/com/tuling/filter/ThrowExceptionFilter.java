package com.tuling.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/4/25.
 */
@Component
@Slf4j
public class ThrowExceptionFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("ThrowExceptionFilter的run方法 ");
        throwExcetion();
        return null;
    }

    private void throwExcetion() {
        throw new RuntimeException("人为抛出异常...");
    }
}
