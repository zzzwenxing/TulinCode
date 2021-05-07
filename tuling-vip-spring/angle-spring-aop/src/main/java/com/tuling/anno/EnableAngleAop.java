package com.tuling.anno;

import com.tuling.core.AngleAutoProxyCreator;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启自定义的aop功能
 * Created by smlz on 2019/6/27.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AngleAutoProxyCreator.class)
public @interface EnableAngleAop {

    /**
     * 暴露代理对象到当前的线程变量中
     * @return
     */
    boolean exposeProxy() default false;
}
