package com.tuling.support.aspect;

import com.tuling.config.AngleDTAutoConfig;
import com.tuling.core.AngleConnection;
import com.tuling.core.AngleGlobalTransactionManager;
import com.tuling.support.holder.AngleDTProperties;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.sql.Connection;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述 用于切入数据源 getConnection()方法的
* @author: smlz
* @createDate: 2019/7/26 21:51
* @version: 1.0
*/
@Aspect
@Order(1)
    public class AngleConnectionAspect {

    @Autowired
    private AngleGlobalTransactionManager angleGlobalTransactionManager;

    @Autowired
    private AngleDTProperties angleDTProperties;

    /**
     * 方法实现说明 环绕通知
     * @author:smlz
     * @param proceedingJoinPoint
     * @return:AngleConnection
     * @date:2019/7/26 21:52
     */
    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Connection cutConnectionMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //返回数据库原生的Connection
        Connection connection = (Connection) proceedingJoinPoint.proceed();
        //保证成我们自己的数据库连接，然后获取控制权
        AngleConnection angleConnection = new AngleConnection(connection,angleGlobalTransactionManager,angleDTProperties);
        return angleConnection;
    }
}
