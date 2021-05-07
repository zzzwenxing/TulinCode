package com.tuling.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切面标识类
 * Created by smlz on 2019/6/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AngleAspect {

    /**
     * 切面执行顺序接口
     * @return
     */
    String order() default "0";
}
