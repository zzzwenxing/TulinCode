package com.tuling.joinpoint.invocation;

import com.tuling.joinpoint.AngleJoinPoint;

/**
 *该接口 表示程序中的一次调用(方法的调用)
 * 调用的过程中能够被方法拦截器拦截
 * Created by smlz on 2019/6/30.
 */
public interface AngleInvocation extends AngleJoinPoint {

    Object[] getArguments();
}
