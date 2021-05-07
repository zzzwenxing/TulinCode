package com.tuling.joinpoint.invocation;


import java.lang.reflect.Method;

/**
 * 描述方法的调用
 * Created by smlz on 2019/6/30.
 */
public interface AngleMethodInvocation extends AngleInvocation {

    Method getMethod();
}
