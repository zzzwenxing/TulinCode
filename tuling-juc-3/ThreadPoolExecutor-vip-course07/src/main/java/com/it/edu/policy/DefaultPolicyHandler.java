package com.it.edu.policy;

import com.it.edu.TulingThreadPoolExecutor;
import com.it.edu.exception.PolicyException;

/**
 * @author ：图灵-杨过
 * @date：2019/7/23
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class DefaultPolicyHandler implements PolicyHandler {

    public DefaultPolicyHandler(){}

    /**
     * 拒绝策略
     *
     * @param task
     * @param executor
     */
    public void rejected(Runnable task, TulingThreadPoolExecutor executor) {
        System.out.println("任务已经满了");
        throw new PolicyException("任务已经满了");
    }
}
