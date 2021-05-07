package com.tuling.joinpoint;

/**
 * 方法连接点接口
 * Created by smlz on 2019/6/30.
 */
public interface AngleJoinPoint {

    /**
     * 获取下一个方法拦截器
     * @return
     * @throws Throwable
     */
    Object proceed() throws Throwable;
}
