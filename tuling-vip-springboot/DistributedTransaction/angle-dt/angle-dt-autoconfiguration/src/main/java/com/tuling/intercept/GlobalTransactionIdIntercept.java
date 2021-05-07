package com.tuling.intercept;


import com.tuling.support.holder.AngleTransactionalHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:全局事务ID拦截器,从header中获取分布式全局ID
* @author: smlz
* @createDate: 2019/7/28 11:09
* @version: 1.0
*/
@Slf4j
public class GlobalTransactionIdIntercept implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        String globalTransactionId =  request.getHeader("globalTransactionId");
        if(StringUtils.isEmpty(globalTransactionId)) {
            log.info("请求头未包含请求参数globalTransactionId:{}",globalTransactionId);
            return false;
        }
        AngleTransactionalHolder.set(globalTransactionId);
        return true;
    }
}
