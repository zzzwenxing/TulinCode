package com.tuling.version3.compent;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by smlz on 2019/8/31.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(TulingImportSelector.class)
public @interface TulingEnableAutoConfig {

}
