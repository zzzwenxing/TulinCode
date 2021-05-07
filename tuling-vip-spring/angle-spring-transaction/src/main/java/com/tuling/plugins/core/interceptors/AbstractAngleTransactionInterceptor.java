package com.tuling.plugins.core.interceptors;

import com.tuling.plugins.core.attribute.AngleTransactionAttribute;
import com.tuling.plugins.core.attribute.DefaultAngleTransactionAttribute;
import com.tuling.plugins.core.source.AngleTransactionAttributeSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 事务拦截器抽象类
 * Created by smlz on 2019/7/2.
 */
@Data
@Slf4j
public abstract class AbstractAngleTransactionInterceptor implements BeanFactoryAware {

    private BeanFactory beanFactory;

    private AngleTransactionAttributeSource angleTransactionAttributeSource;

    private PlatformTransactionManager transactionManager;

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    protected Object invokeWithinTransaction(Method method, Class<?> targetClass, final InvocationCallback invocation)
            throws Throwable {

        //获取事务属性
        AngleTransactionAttribute txAttr = getAngleTransactionAttributeSource().getTransactionAttribute(method,targetClass);
        //获取事务管理器
        PlatformTransactionManager transactionManager = determineTransactionManager();

        Connection connection = openTransactionIfNecessary(transactionManager);
        Object retVal = null;
        try {
            //调用目标函数
            retVal =  invocation.proceedWithInvocation();
            //提交事务
            connection.commit();
        } catch (Exception e) {
            log.error("执行目标方法异常:{}",e.getMessage());
            //回滚事务
            completeTransactionAfterThrowing(connection,txAttr,e);

            throw e;

        }finally {
            //关闭连接
            connection.close();
        }

        return retVal;
    }

    /**
     * 开启事务 如果有必要的话
     * @param txManager 事务管理器
     * @return
     */
    protected  Connection openTransactionIfNecessary(PlatformTransactionManager txManager) throws SQLException {
        DataSourceTransactionManager dataSourceTransactionManager = (DataSourceTransactionManager) txManager;

        DataSource dataSource = dataSourceTransactionManager.getDataSource();
        //获取数据库连接
        Connection connection = dataSource.getConnection();

        if(connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }
        return connection;
    }

    /**
     * 获取事务管理器
     * @return PlatformTransactionManager
     */
    protected  PlatformTransactionManager determineTransactionManager(){
        return beanFactory.getBean(PlatformTransactionManager.class);
    }


    protected interface InvocationCallback {

        Object proceedWithInvocation() throws Throwable;
    }


    /**
     * 处理异常是否需要被回滚
     * @param angleTransactionAttribute
     * @param e
     */
    public void completeTransactionAfterThrowing(Connection connection,AngleTransactionAttribute angleTransactionAttribute,Exception e) throws SQLException {
        DefaultAngleTransactionAttribute defaultAngleTransactionAttribute = (DefaultAngleTransactionAttribute) angleTransactionAttribute;
        if(defaultAngleTransactionAttribute.rollbackOn(e)) {
            connection.rollback();
        }else {
            connection.commit();
        }
    }


}
