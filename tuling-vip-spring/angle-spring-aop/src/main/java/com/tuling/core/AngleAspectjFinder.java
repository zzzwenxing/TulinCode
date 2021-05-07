package com.tuling.core;

import com.tuling.advice.*;
import com.tuling.advisor.AngleAdvisor;
import com.tuling.advisor.AngleAspectPointCutAdvisor;
import com.tuling.anno.*;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.Advice;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 天使切面查找类
 * Created by smlz on 2019/6/27.
 */
@Slf4j
public class AngleAspectjFinder {

    private static final Class<?>[] ASPECTJ_ANNOTATION_CLASSES = new Class<?>[] {
            AngleAfter.class, AngleAfterThrowing.class, AngleAfterReturning.class, AngleBefore.class};


    /**
     * 根据class类型判断是不是切面类
     * @param clazz
     * @return
     */
    public static boolean isAngleAspect(Class<?> clazz) {
        //做非空判断
        if(clazz == null ) {
            return false;
        }
        //获取class对象上是否标注了AngleAspect注解
        AngleAspect angleAspect = clazz.getAnnotation(AngleAspect.class);
        if(angleAspect == null) {
            return false;
        }
        log.info("已经探测到切面类:{}",clazz.getCanonicalName());
        return true;
    }

    /**
     * 从切面信息中找出增强器
     * @param clazz 切面类
     * @return List<AngleAdvisor>
     */
    public static List<AngleAdvisor> getAdvisor4Aspect(Class<?> clazz, ApplicationContext applicationContext) throws NoSuchMethodException {
        List<AngleAdvisor> angleAdvisorList = new ArrayList<AngleAdvisor>();
        //去切面类中找 注解
        List<Method> annoMethods = getAnnoMethod(clazz);

        //没有切面方法 返回空集合
        if(annoMethods.isEmpty()) {
            return angleAdvisorList;
        }

        //获取切点表达式
        String expression = parsePointCutExp(clazz);

        //解析出方法上的注解 构建成一个个的增强器
        for(Method method:annoMethods) {
            if(method.getAnnotation(AngleBefore.class)!=null) {
                //前置通知
                AngleMethodBeforeAdvice angleMethodBeforeAdvice = new AngleMethodBeforeAdvice(method,clazz,applicationContext);
                //前置通知者(包括了通知+切点)
                AngleAspectPointCutAdvisor angleAspectPointCutAdvisor = new AngleAspectPointCutAdvisor(angleMethodBeforeAdvice,expression);
                angleAspectPointCutAdvisor.setAngleAspectAdviceMethod(method.getName());
                angleAdvisorList.add(angleAspectPointCutAdvisor);
            }
            if(method.getAnnotation(AngleAfter.class)!=null) {
                //创建后置通知切面
                AngleMethodAfterAdvice angleMethodAfterAdvice = new AngleMethodAfterAdvice(method,clazz,applicationContext);
                AngleAspectPointCutAdvisor angleAspectPointCutAdvisor = new AngleAspectPointCutAdvisor(angleMethodAfterAdvice,expression);
                angleAdvisorList.add(angleAspectPointCutAdvisor);
            }
            if(method.getAnnotation(AngleAfterReturning.class)!=null) {
                //创建返回通知切面
                AngleMethodAfterReturingAdvice angleMethodAfterReturingAdvice = new AngleMethodAfterReturingAdvice(method,clazz,applicationContext);
                AngleAspectPointCutAdvisor angleAspectPointCutAdvisor = new AngleAspectPointCutAdvisor(angleMethodAfterReturingAdvice,expression);
                angleAdvisorList.add(angleAspectPointCutAdvisor);
            }
            if(method.getAnnotation(AngleAfterThrowing.class)!=null) {
                //异常通知
                AngleMethodThrowingAdvice angleMethodThrowingAdvice = new AngleMethodThrowingAdvice(method,clazz,applicationContext);
                AngleAspectPointCutAdvisor angleAspectPointCutAdvisor = new AngleAspectPointCutAdvisor(angleMethodThrowingAdvice,expression);
                angleAdvisorList.add(angleAspectPointCutAdvisor);
            }
        }
        return angleAdvisorList;
    }

    /**
     * 获取切面类标注了注解的方法
     * @param clazz
     * @return
     */
    private static List<Method> getAnnoMethod(Class<?> clazz) {
        List<Method> annoMethods = new ArrayList<Method>();
        Method[] allMethod = clazz.getDeclaredMethods();

        //查找带有注解的方法
        for(Method method:allMethod) {
            if(method.getAnnotation(AngleBefore.class)!=null||method.getAnnotation(AngleAfter.class)!=null
               ||method.getAnnotation(AngleAfterReturning.class)!=null||method.getAnnotation(AngleAfterThrowing.class)!=null) {
                annoMethods.add(method);
            }
        }
        return annoMethods;
    }

    /**
     * 从给定注解中解析出表达式
     * @return AngleAdvice
     */
    private static String  parsePointCutExp(Class<?> clazz)  {
        Method []methods = clazz.getDeclaredMethods();
        for(Method method :methods) {
            AnglePointCut anglePointCut = method.getAnnotation(AnglePointCut.class);
            if(anglePointCut!=null) {
                return anglePointCut.value();
            }
        }
        log.warn("@AnglePointCut中的切点表达式不存在");
        throw new RuntimeException("AnglePointCut中的切点表达式不存在");
    }


}
