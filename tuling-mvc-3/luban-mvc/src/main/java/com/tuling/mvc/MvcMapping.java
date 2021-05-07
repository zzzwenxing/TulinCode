package com.tuling.mvc;/**
 * Created by Administrator on 2018/9/17.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Tommy
 *         Created by Tommy on 2018/9/17
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface MvcMapping {
    public String value();
}
