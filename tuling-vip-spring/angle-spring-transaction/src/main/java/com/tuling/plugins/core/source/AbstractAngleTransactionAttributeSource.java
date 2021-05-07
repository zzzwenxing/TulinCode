package com.tuling.plugins.core.source;


import com.tuling.plugins.core.attribute.AngleTransactionAttribute;
import com.tuling.plugins.core.attribute.DefaultAngleTransactionAttribute;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 事务属性源的抽象类
 * Created by smlz on 2019/7/2.
 */
public abstract class AbstractAngleTransactionAttributeSource implements AngleTransactionAttributeSource {

    //事务注解属性缓存
    private final Map<Object, AngleTransactionAttribute> attributeCache =
            new ConcurrentHashMap<Object, AngleTransactionAttribute>(1024);

    //不支持的空事务
    private static final AngleTransactionAttribute NULL_TRANSACTION_ATTRIBUTE = new DefaultAngleTransactionAttribute() {
        @Override
        public String toString() {
            return "null";
        }
    };


    /**
     * 从传入的方法和类上获取事务属性
     * @param method 执行的方法
     * @param targetClass 执行的目标类
     * @return AngleTransactionAttribute
     */
    public AngleTransactionAttribute getTransactionAttribute(Method method, Class<?> targetClass) {
        if (method.getDeclaringClass() == Object.class) {
            return null;
        }

        //先尝试从缓存中获取
        Object cacheKey = getCacheKey(method, targetClass);
        AngleTransactionAttribute cached = attributeCache.get(cacheKey);
        //缓存中有
        if(cached!=null) {
            //为空事务
            if(cached == NULL_TRANSACTION_ATTRIBUTE) {
                return null;
            }
            return cached;
        }

        //真正的解析事务
        AngleTransactionAttribute angleTransactionAttribute = computeTransactionAttribute(method,targetClass);

        if(angleTransactionAttribute == null) {
            attributeCache.put(cacheKey,NULL_TRANSACTION_ATTRIBUTE);
        }else {//有事务属性
            attributeCache.put(cacheKey,angleTransactionAttribute);
            if (angleTransactionAttribute instanceof DefaultTransactionAttribute) {
                //获取事务属性方法的描述符
                String methodIdentification = ClassUtils.getQualifiedMethodName(method, targetClass);
                ((DefaultTransactionAttribute) angleTransactionAttribute).setDescriptor(methodIdentification);
            }

        }
        return angleTransactionAttribute;
    }

    /**
     * 计算出解析事务属性
     * @return
     */
    protected  AngleTransactionAttribute computeTransactionAttribute(Method method, Class<?> targetClass) {
        //若传入进来的class是接口的话
        if(targetClass.isInterface()) {
            //获取实现类
            Class<?> userClass = ClassUtils.getUserClass(targetClass);
            //获取实现类中对应的方法
            Method specificMethod = ClassUtils.getMostSpecificMethod(method, userClass);
            //在这里不考虑从类或者 接口上去找事务注解了
            AngleTransactionAttribute txAttr = findTransactionAttribute(specificMethod);
            if(txAttr!=null) {
                return txAttr;
            }
        }else {//传递进来是实现类
            AngleTransactionAttribute txAttr = findTransactionAttribute(method);
            if(txAttr!=null) {
                return txAttr;
            }
        }
        return null;
    }

    private String getCacheKey(Method method,Class<?> targetClass) {
        return targetClass.getCanonicalName()+method.getName();
    }

    protected abstract AngleTransactionAttribute findTransactionAttribute(Method method);
}
