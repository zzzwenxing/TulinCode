package com.tuling.support.anno;


import com.tuling.support.enumaration.TransactionalTypeEunm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @vlog: 高于生活，源于生活
* @Author: smlz
* @CreateDate: 2019/7/26 19:43
* @Version: 1.0
*/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AngleTransactional {

    TransactionalTypeEunm transType();
}
