package com.tuling.plugins.anno;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 事务注解
 * Created by smlz on 2019/7/2.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AngleTransactionl {

    @AliasFor("transactionManager")
    String value() default "";

    @AliasFor("value")
    String transactionManager() default "";

    /**
     * 事务的默认传播行为
     * @return
     */
    Propagation propagation() default Propagation.REQUIRED;

    /**
     * 事务的隔离级别
     * @return
     */
    Isolation isolation() default Isolation.DEFAULT;

    /**
     * 超时
     * @return
     */
    int timeout() default TransactionDefinition.TIMEOUT_DEFAULT;

    /**
     * 是否为只读事务
     * @return
     */
    boolean readOnly() default false;

    /**
     * 对哪种异常回滚
     * @return
     */
    Class<? extends Throwable>[] rollbackFor() default {};

    /**
     * 哪种类型不回滚
     * @return
     */
    Class<? extends Throwable>[] noRollbackFor() default {};

}
