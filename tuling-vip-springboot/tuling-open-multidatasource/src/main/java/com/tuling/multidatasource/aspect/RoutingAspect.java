package com.tuling.multidatasource.aspect;

import com.tuling.multidatasource.annotation.Router;
import com.tuling.multidatasource.core.ITulingRouting;
import com.tuling.multidatasource.dynamicdatasource.MultiDataSourceHolder;
import com.tuling.multidatasource.enumuration.MultiDsErrorEnum;
import com.tuling.multidatasource.exception.LoadRoutingStategyUnMatch;
import com.tuling.multidatasource.exception.ParamsNotContainsRoutingField;
import com.tuling.multidatasource.exception.RoutingFiledArgsIsNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 拦截切面组件
 * Created by smlz on 2019/4/17.
 */
@Component
@Aspect
@Slf4j
public class RoutingAspect {

    @Autowired
    private ITulingRouting routing;


    @Pointcut("@annotation(com.tuling.multidatasource.annotation.Router)")
    public void pointCut(){};

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws LoadRoutingStategyUnMatch, RoutingFiledArgsIsNull, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        long beginTime = System.currentTimeMillis();
        //获取方法调用名称
        Method method = getInvokeMethod(joinPoint);

        //获取方法指定的注解
        Router router = method.getAnnotation(Router.class);
        //获取指定的路由key
        String routingFiled = router.routingFiled();

        //获取方法入参
        Object[] args = joinPoint.getArgs();


        boolean havingRoutingField = false;

        if(args!=null && args.length>0) {
            for(int index=0;index<args.length;index++) {
                String routingFieldValue = BeanUtils.getProperty(args[index],routingFiled);
                if(!StringUtils.isEmpty(routingFieldValue)) {
                    String dbKey = routing.calDataSourceKey(routingFieldValue);
                    String tableIndex = routing.calTableKey(routingFieldValue);
                    log.info("选择的Dbkey是:{},tableKey是:{}",dbKey,tableIndex);
                    havingRoutingField = true;
                    break;
                }else {

                }
            }

            //判断入参中没有路由字段
            if(!havingRoutingField) {
                log.warn("入参{}中没有包含路由字段:{}",args,routingFiled);
                throw new ParamsNotContainsRoutingField(MultiDsErrorEnum.PARAMS_NOT_CONTAINS_ROUTINGFIELD);
            }
        }

    }

    private Method getInvokeMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        return targetMethod;
    }

    /**
     * 清除线程缓存
     * @param joinPoint
     */
    @After("pointCut()")
    public void methodAfter(JoinPoint joinPoint){
        MultiDataSourceHolder.clearDataSourceKey();
        MultiDataSourceHolder.clearTableIndex();
    }
}
