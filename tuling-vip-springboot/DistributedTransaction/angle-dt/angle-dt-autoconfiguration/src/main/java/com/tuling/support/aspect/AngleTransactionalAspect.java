package com.tuling.support.aspect;

import com.tuling.core.AngleGlobalTransactionManager;
import com.tuling.core.AngleTrancationBulider;
import com.tuling.core.ChildTransactionObj;
import com.tuling.support.anno.AngleTransactional;
import com.tuling.support.enumaration.TransactionalTypeEunm;
import com.tuling.support.enumaration.TransationalEnumStatus;
import com.tuling.support.holder.AngleTransactionalHolder;
import com.tuling.support.utils.AngleDTUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述 事务切面
* @author: smlz
* @createDate: 2019/7/26 22:00
* @version: 1.0
*/
@Aspect
@Order(0)
@Slf4j
public class AngleTransactionalAspect {

    @Autowired
    private AngleGlobalTransactionManager angleGlobalTransactionManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.tuling.support.anno.AngleTransactional)")
    public void pointCut() {};

    @Around("pointCut()")
    public void invoke(ProceedingJoinPoint joinPoint) throws Exception {

        Method targetMethod = getTargetMethod(joinPoint);

        //获取目标方法的注解
        AngleTransactional angleTransactional = targetMethod.getAnnotation(AngleTransactional.class);

        //获取注解属性对象
        TransactionalTypeEunm transactionalTypeEunm = angleTransactional.transType();

        //判断是不是分布式事务开始节点
        if(transactionalTypeEunm.getCode() == TransactionalTypeEunm.BEGIN.getCode()) {

            //生成全局唯一ID
            String gloableTransactionId = AngleDTUtils.generatorGlobalTransactionalId();

            //放入线程变量中
            AngleTransactionalHolder.set(gloableTransactionId);
        }

        //使用建造者模式来构建子事务对象(此时的事务对象的状态是中间状态 WATING状态)
        ChildTransactionObj childTransactionObj = builderChildTransactionObj(transactionalTypeEunm.getCode(),TransationalEnumStatus.WATING.getCode());

        try {

            //把子事务对象上报到分布式事务管理中心
            angleGlobalTransactionManager.save2Redis(childTransactionObj);

            //调用目标方法，我们需要在目标方法新开一个线程去监控redis的值是否变化来决定,本地事务是提交还是回滚
            joinPoint.proceed();

            //目标方法没有抛出异常  修改中间状态为COMMIT状态
            childTransactionObj.setTransationalEnumStatusCode(TransationalEnumStatus.COMMIT.getCode());
            angleGlobalTransactionManager.save2Redis(childTransactionObj);

        } catch (Throwable throwable) {
            log.error("保存子事务状态到redis中抛出异常:globalId:{},childId:{},异常:{}",childTransactionObj.getGlobalTransactionalId(),childTransactionObj.getChildTransactionalId(),throwable.getStackTrace());
            //调用本地事务方法异常的话,修改当前子事务状态为ROLLBACK状态
            childTransactionObj.setTransationalEnumStatusCode(TransationalEnumStatus.ROLLBACK.getCode());
            angleGlobalTransactionManager.save2Redis(childTransactionObj);
            throw new RuntimeException(throwable.getMessage());
        }

    }

    /**
     * 方法实现说明:获取目标方法
     * @author:smlz
     * @param ProceedingJoinPoint
     * @return: Method
     * @date:2019/7/26 21:54
     */
    private Method getTargetMethod(ProceedingJoinPoint proceedingJoinPoint){
        MethodSignature  signature = (MethodSignature)proceedingJoinPoint.getSignature();
        return signature.getMethod();
    }

    /**
     * 方法实现说明 构造子事务对象
     * @author:smlz
     * @param TransactionalTypeEunm
     * @return: ChildTransactionObj
     * @date:2019/7/26 22:01
     */
    private ChildTransactionObj builderChildTransactionObj(Integer TransactionalTypeEunmCode ,Integer TransationalEnumStatusCode){
        AngleTrancationBulider angleTrancationBulider = new AngleTrancationBulider();
        String childTransId = AngleDTUtils.generatorChildTransactionalId();
        AngleTransactionalHolder.setChild(childTransId);
        return angleTrancationBulider.buliderTransactionalTypeEunmCode(TransactionalTypeEunmCode)
                              .builderChildTransactionalId(childTransId)
                              .builderTransationalEnumStatus(TransationalEnumStatusCode)
                              .buliderGlobalTransactionId(AngleTransactionalHolder.get())
                              .builder();
    }
}
