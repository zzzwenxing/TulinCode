package com.tuling.advisor;

import com.tuling.advice.AngleAdvice;
import com.tuling.pointcut.AngleAspectExpPointCut;
import com.tuling.pointcut.AnglePointCut;
import lombok.Data;
import org.aopalliance.aop.Advice;

/**
 * Created by smlz on 2019/6/28.
 */
@Data
public class AngleAspectPointCutAdvisor  implements AnglePointcutAdvisor{

    //用户保存通知
    private AngleAdvice angleAdvice;

    //切点表达式
    private String expression;

    //我们定义切面中的方法(全路径)
    private String angleAspectAdviceMethod;

    //基于aspectj的切点表达式对象
    private AngleAspectExpPointCut angleAspectExpPoint;
    //简单方法名称
    private String methodName;

    //当前需要执行的切面对象
    private Object currentAspect;


    public AngleAspectPointCutAdvisor(AngleAdvice angleAdvice, String expression) {
        this.angleAdvice = angleAdvice;
        this.expression = expression;
        this.angleAspectExpPoint = new AngleAspectExpPointCut(this.expression);
    }



    public AnglePointCut getAnglePointCut() {
        return this.angleAspectExpPoint;
    }

    public AngleAdvice getAdvice() {
        return this.angleAdvice;
    }
}
