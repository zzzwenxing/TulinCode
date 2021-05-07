package com.tuling.multidatasource.annotation;

import com.tuling.multidatasource.constant.TulingConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 路由注解
 * Created by smlz on 2019/4/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Router {

    String routingFiled() default TulingConstant.DEFAULT_ROUTING_FIELD;

}
