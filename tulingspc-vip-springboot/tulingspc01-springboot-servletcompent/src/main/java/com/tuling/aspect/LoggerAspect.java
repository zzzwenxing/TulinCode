package com.tuling.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/7/12.
 */
@Aspect
@Slf4j
@Component
public class LoggerAspect {

    @Pointcut("@annotation(com.tuling.anno.AngleLogger)")
    public void pointCut(){};

    @Before("pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        log.info("方法名:{}",joinPoint.getSignature().getName());
        log.info("参数:{}",joinPoint.getArgs());
    }
}
