package com.tuling.filter;


import com.alibaba.fastjson.JSON;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/4/25.
 */
@Slf4j
@Component
public class TulingErrorFilter extends SendErrorFilter {

    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            HttpServletRequest request = ctx.getRequest();

            HttpServletResponse response = ctx.getResponse();
            response.setContentType("appliation/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            Map<String,Object> errorMap = new HashMap<>();
            errorMap.put("code","-1");
            errorMap.put("errMsg",exception.getThrowable().getCause().getMessage());

            response.getWriter().write(JSON.toJSONString(errorMap));
        }
        catch (Exception ex) {
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

}
