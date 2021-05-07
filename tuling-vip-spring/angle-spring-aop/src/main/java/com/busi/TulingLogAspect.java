package com.busi;

import com.tuling.anno.*;
import com.tuling.joinpoint.MethodJoinPoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by smlz on 2019/6/10.
 */
@AngleAspect
public class TulingLogAspect {

    @AnglePointCut("execution(* com.busi.TulingCalculate.*(..))")
    public void pointCut(){};

    @AngleBefore(value = "pointCut()")
    public void methodBefore(MethodJoinPoint joinPoint){

        String methodName = joinPoint.getTargetMethod();
        System.out.println("执行目标方法【"+methodName+"】之前执行<前置通知>,入参"+ Arrays.asList(joinPoint.getArgs()));
    }

    @AngleAfter(value = "pointCut()")
    public void methodAfter(MethodJoinPoint joinPoint) {
        String methodName = joinPoint.getTargetMethod();
        System.out.println("执行目标方法【"+methodName+"】之前执行<后置通知>,入参"+Arrays.asList(joinPoint.getArgs()));
    }

    @AngleAfterReturning(value = "pointCut()")
    public void methodReturning(MethodJoinPoint joinPoint ) {
        String methodName = joinPoint.getTargetMethod();
        System.out.println("执行目标方法【"+methodName+"】之前执行<返回通知>,入参"+Arrays.asList(joinPoint.getArgs()));
    }

    @AngleAfterThrowing(value = "pointCut()")
    public void methodAfterThrowing(MethodJoinPoint joinPoint) {
        String methodName = joinPoint.getTargetMethod();
        System.out.println("执行目标方法【"+methodName+"】之前执行<异常通知>,入参"+Arrays.asList(joinPoint.getArgs()));
    }
}
