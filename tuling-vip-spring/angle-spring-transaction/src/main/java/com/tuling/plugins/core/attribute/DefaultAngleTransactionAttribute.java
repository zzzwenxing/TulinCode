package com.tuling.plugins.core.attribute;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 默认的事务属性
 * Created by smlz on 2019/7/2.
 */
@Slf4j
@Data
public class DefaultAngleTransactionAttribute implements AngleTransactionAttribute {

    private String transactionManager;

    private String methodDescriptor;

    private int propagationBehavior = PROPAGATION_REQUIRED;

    private int isolationLevel = ISOLATION_DEFAULT;

    private int timeout = TIMEOUT_DEFAULT;

    private boolean readOnly = false;

    private List<Class<?>> rollbackFor;

    private List<Class<?>> noRollBackFor;

    public String transactionManagerName() {
        return this.transactionManager;
    }

    public boolean rollbackOn(Throwable ex) {
        return (ex instanceof RuntimeException || ex instanceof Error);
    }

    public int getPropagationBehavior() {
        return this.propagationBehavior;
    }

    public int getIsolationLevel() {
        return this.isolationLevel;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public String getName() {
        return this.methodDescriptor;
    }
}
