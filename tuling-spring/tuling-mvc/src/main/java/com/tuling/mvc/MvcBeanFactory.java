package com.tuling.mvc;
/**
 * Created by Administrator on 2018/9/1.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Tommy
 *         Created by Tommy on 2018/9/1
 **/
public class MvcBeanFactory {
    private ApplicationContext applicationContext;

    public MvcBeanFactory(ApplicationContext applicationContext) {
        Assert.notNull(applicationContext, "argument 'applicationContext' must not be null");
        this.applicationContext = applicationContext;
        loadApiFromSpringBeans();
    }

    // API 接口住的地方 
    private HashMap<String, MvcBean> apiMap = new HashMap<String, MvcBean>();


    private void loadApiFromSpringBeans() {
        apiMap.clear();
        // ioc 所有BEan
        // spring ioc 扫描
        String[] names = applicationContext.getBeanDefinitionNames();
        Class<?> type;
        for (String name : names) {
            type = applicationContext.getType(name);
            for (Method m : type.getDeclaredMethods()) {
                // 通过反射拿到HttpMapping注解
                MvcMapping MvcMapping = m.getAnnotation(MvcMapping.class);
                if (MvcMapping != null) {
                    // 封装成一个 MVC bean
                    addApiItem(MvcMapping, name, m);
                }
            }
        }
    }

    public MvcBean getMvcBean(String apiName) {
        return apiMap.get(apiName);
    }

    /**
     * 添加api <br/>
     *
     * @param MvcMapping api配置
     * @param beanName   beanq在spring context中的名称
     * @param method
     */
    private void addApiItem(MvcMapping MvcMapping, String beanName, Method method) {
        MvcBean apiRun = new MvcBean();
        apiRun.apiName = MvcMapping.value();
        apiRun.targetMethod = method;
        apiRun.targetName = beanName;
        apiRun.context = this.applicationContext;
        apiMap.put(MvcMapping.value(), apiRun);
    }


    public boolean containsApi(String apiName, String version) {
        return apiMap.containsKey(apiName + "_" + version);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    // 用于执行对应的API方法，
    //
    public static class MvcBean {
        String apiName;  //bit.api.user.getUser

        String targetName; //ioc bean 名称

        Object target; // UserServiceImpl 实例
        Method targetMethod; // 目标方法 getUser
        ApplicationContext context;


        public Object run(Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            // 懒加载
            if (target == null) {
                // spring ioc 容器里面去服务Bean 比如GoodsServiceImpl
                target = context.getBean(targetName);
            }
            return targetMethod.invoke(target, args);
        }

        public Class<?>[] getParamTypes() {
            return targetMethod.getParameterTypes();
        }

        public String getApiName() {
            return apiName;
        }

        public String getTargetName() {
            return targetName;
        }

        public Object getTarget() {
            return target;
        }

        public Method getTargetMethod() {
            return targetMethod;
        }

    }
}
