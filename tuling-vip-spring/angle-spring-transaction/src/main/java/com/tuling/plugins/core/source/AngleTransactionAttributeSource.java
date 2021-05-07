package com.tuling.plugins.core.source;


import com.tuling.plugins.core.attribute.AngleTransactionAttribute;

import java.lang.reflect.Method;

/**
 * 事务属性源接口
 * Created by smlz on 2019/7/2.
 */
public interface AngleTransactionAttributeSource {

    /**
     * 获取目标方法上的标注了事务属性接口
     * @param method 执行的方法
     * @param targetClass 执行的目标类
     * @return 方法上的事务属性对象
     */
    AngleTransactionAttribute getTransactionAttribute(Method method, Class<?> targetClass);
}
