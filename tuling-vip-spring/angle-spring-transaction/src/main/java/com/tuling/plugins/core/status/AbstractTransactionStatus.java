package com.tuling.plugins.core.status;


import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * 事务状态接口
 * Created by smlz on 2019/7/2.
 */

public abstract class AbstractTransactionStatus implements TransactionStatus {

    private boolean rollbackOnly = false;

    private boolean completed = false;

    public void setCompleted() {
        this.completed = true;
    }

    public boolean isNewTransaction() {
        return false;
    }

    public boolean hasSavepoint() {
        return false;
    }

    public void setRollbackOnly() {
        this.rollbackOnly = true;
    }

    public boolean isRollbackOnly() {
        return this.rollbackOnly;
    }

    public void flush() {

    }

    public boolean isCompleted() {
        return this.completed;
    }

    public Object createSavepoint() throws TransactionException {
        return null;
    }

    public void rollbackToSavepoint(Object savepoint) throws TransactionException {

    }

    public void releaseSavepoint(Object savepoint) throws TransactionException {

    }
}
