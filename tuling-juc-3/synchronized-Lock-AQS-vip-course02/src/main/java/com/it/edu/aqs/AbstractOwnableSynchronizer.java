package com.it.edu.aqs;

/**
 * @author ：图灵-杨过
 * @date：2019/7/4
 * @version: V1.0
 * @slogan:天下风云出我辈，一入代码岁月催 description：
 */
public abstract class AbstractOwnableSynchronizer implements java.io.Serializable {
    private static final long serialVersionUID = 3737899427754241961L;

    protected AbstractOwnableSynchronizer() { }

    /**
     * 独占模式同步器的当前持有线程.
     * transient关键字表示属性不参与序列化
     */
    private transient Thread exclusiveOwnerThread;

    protected final void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }
}
