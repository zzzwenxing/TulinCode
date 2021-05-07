package com.tuling.support.anno;

import com.tuling.support.marker.ConfigMarker;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启自定义的aop功能
 * Created by smlz on 2019/6/27.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ConfigMarker.class)
public @interface EnableAngleDistributedTransactional {

}
