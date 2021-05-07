package com.tuling.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by smlz on 2019/4/25.
 */
@Slf4j
@Component
public class TulingAuthPreFilter extends ZuulFilter {
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
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        log.info("请求的方法:{},请求的路径:{}",request.getMethod(),request.getRequestURL().toString());
        String accessToken = request.getHeader("accessToken");
        if(StringUtils.isEmpty(accessToken)) {
            //不进行路由
            requestContext.setSendZuulResponse(false);
            //设置返回码
            requestContext.setResponseStatusCode(401);
            //设置返回体
            requestContext.setResponseBody("权限不够");
            requestContext.set("isSuccess",false);
        }
        return null;
    }
}
