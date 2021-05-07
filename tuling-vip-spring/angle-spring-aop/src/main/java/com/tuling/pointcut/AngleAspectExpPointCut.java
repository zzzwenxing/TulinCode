package com.tuling.pointcut;

import com.busi.TulingCalculate;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;

/**
 * 天使框架中的 aspect 表达式切点类
 * Created by smlz on 2019/6/28.
 */
public class AngleAspectExpPointCut implements AnglePointCut {

    /**
     * aspect 中的切点解析器
     */
    private static PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution();

    /**
     * 切点表达式 "execution(* com.busi.TulingCalculate.*(..))"字符串
     */
    private String expression;

    /**
     * 切点表达式对象
     */
    private PointcutExpression pointcutExpression;

    public AngleAspectExpPointCut(String expression) {
        //保存切点表达式
        this.expression = expression;
        //根据切点表达式字符串构建切点表达式对象
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }


    /**
     * 匹配目标类
     * @param targetClass 正在创建的类
     * @return true|false
     */
    public boolean matchsClass(Class<?> targetClass) {
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    /**
     * 匹配目标方法
     * @param method 当前正在执行的类上的方法
     * @param targetClass 正在调用的类
     * @return true|false
     */
    public boolean matchsMethod(Method method, Class<?> targetClass) {
        ShadowMatch sm = pointcutExpression.matchesMethodExecution(method);
        return sm.alwaysMatches();
    }

}
