package com.tuling.joinpoint;

import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;

/**
 * 方法连接点
 * Created by smlz on 2019/7/1.
 */
@Data
public class MethodJoinPoint  {


    private Object target;

    private Object[] args;

    private String targetMethod;


}
