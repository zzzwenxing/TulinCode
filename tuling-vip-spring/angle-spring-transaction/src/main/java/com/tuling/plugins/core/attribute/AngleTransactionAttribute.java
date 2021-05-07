package com.tuling.plugins.core.attribute;

import org.springframework.transaction.TransactionDefinition;

/**
 * 事务属性接口
 * Created by smlz on 2019/7/2.
 */
public interface AngleTransactionAttribute extends TransactionDefinition {

    /**
     * 获取事务管理器的名称 用来指定获取正确的事务管理器
     * @return
     */
    String transactionManagerName();

    /**
     * 对哪种异常进行回滚
     * @param ex 异常类型
     * @return
     */
    boolean rollbackOn(Throwable ex);
}
