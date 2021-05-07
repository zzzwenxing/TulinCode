package com.tuling.advice;

import com.tuling.joinpoint.MethodJoinPoint;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 通知抽象类
 * Created by smlz on 2019/6/30.
 */
@Slf4j
@Data
public abstract class AngleAbstractAdvice  implements AngleAdvice {

    private Method method;

    private Class<?> aspectClass;

    private ApplicationContext applicationContext;


    public AngleAbstractAdvice(Method method,Class<?> aspectClass,ApplicationContext applicationContext) {
        this.method = method;
        this.aspectClass = aspectClass;
        this.applicationContext =applicationContext;
    }

    /**
     * 调用通知中的具体方法
     * @param method 通知中的方法
     * @param args 调用参数
     * @param targetMethod 目标方法
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void invokeAdvisorMethod(Method method,Object[] args,Method targetMethod) throws InvocationTargetException, IllegalAccessException {

        MethodJoinPoint methodJoinPoint = new MethodJoinPoint();
        methodJoinPoint.setArgs(args);
        methodJoinPoint.setTargetMethod(targetMethod.getName());
        Object aspectObj =  applicationContext.getBean(aspectClass);
        method.invoke(aspectObj,methodJoinPoint);
    }

}
