package com.tuling.plugins.core.status;

import lombok.Data;

/**
 * 事务状态
 * Created by smlz on 2019/7/2.
 */
@Data
public class DefaultAngleTransactionStatus extends AbstractTransactionStatus {

    private final Object transaction;

    private final boolean newTransaction;

    private final boolean newSynchronization;

    private final boolean readOnly;



    public Object getTransaction() {
        return transaction;
    }

    public boolean isNewTransaction() {
        return (hasTransaction() && this.newTransaction);
    }

    public boolean hasTransaction() {
        return (this.transaction != null);
    }

    public boolean isNewSynchronization() {
        return newSynchronization;
    }

    public boolean isReadOnly() {
        return readOnly;
    }
}
