package com.tuling.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 异常通知
 * Created by smlz on 2019/6/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AngleAfterThrowing {

    /**
     * 描述切点的值
     * @return
     */
    String value();

}
