package com.tuling.mvc;/**
 * Created by Administrator on 2018/9/17.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author Tommy
 *         Created by Tommy on 2018/9/17
 **/
public class MvcBeanFactory {
    private ApplicationContext applicationContext;
    // 注册器
    private HashMap<String, MvcBean> apiMap = new HashMap<String, MvcBean>();

    public MvcBeanFactory(ApplicationContext applicationContext) {
        Assert.notNull(applicationContext, "argument 'applicationContext' must not be null");
        this.applicationContext = applicationContext;
        loadApiFromSpringBeans();
    }


    private void loadApiFromSpringBeans() {
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

    public MvcBean getMvcBean(String url) {
        return apiMap.get(url);
    }

    private void addApiItem(MvcMapping MvcMapping, String beanName, Method method) {
        MvcBean apiRun = new MvcBean();
        apiRun.apiName = MvcMapping.value();
        apiRun.targetMethod = method;
        apiRun.targetName = beanName;
        apiRun.context = this.applicationContext;
        apiMap.put(MvcMapping.value(), apiRun);
    }

    public static class MvcBean {
        String apiName;
        String targetName;
        Object target;
        Method targetMethod;
        ApplicationContext context;

        public Object run(Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (target == null) {
                // spring ioc 容器里面去服务Bean 比如GoodsServiceImpl
                target = context.getBean(targetName);
            }
            return targetMethod.invoke(target, args);
        }

        public Method getTargetMethod() {
            return targetMethod;
        }
    }

}
