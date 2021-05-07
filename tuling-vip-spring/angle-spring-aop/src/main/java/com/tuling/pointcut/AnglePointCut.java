package com.tuling.pointcut;

import java.lang.reflect.Method;

/**
 * 方法切点(实现类 可以有aspectj的 也可能有reg正则表达式的(扩展自己去实现) 我们这里主要讲解aspectj匹配)
 * Created by smlz on 2019/6/28.
 */
public interface AnglePointCut {

    /**
     * 根据当前的正在创建的类来判断该类是否满足切点表达式
     * @param targetClass 正在创建的类
     * @return true|false
     */
    boolean matchsClass(Class<?> targetClass);

    /**
     * 匹配方法
     * @param method 当前正在执行的类上的方法
     * @param targetClass 正在调用的类
     * @return true|false
     */
    boolean matchsMethod(Method method, Class<?> targetClass);
}
