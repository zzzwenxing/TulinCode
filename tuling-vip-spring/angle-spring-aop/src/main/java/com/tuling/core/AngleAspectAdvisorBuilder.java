package com.tuling.core;

import com.tuling.advice.AngleAdvice;
import com.tuling.advisor.AngleAdvisor;
import lombok.Data;
import org.springframework.context.ApplicationContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 帮助我们找到标注了天使切面的类,然后把切面类中的切面方法
 * 构建为成增强器(在代理对象中需要织入的对象)
 * Created by smlz on 2019/6/27.
 */
@Data
public class AngleAspectAdvisorBuilder {

    /**
     * 用来保存天使框架中 切面的bean的名称
     */
    private List<String> angleAspectBeanNames = new ArrayList<String>();

    private final Map<String, List<AngleAdvisor>> advisorsCache = new ConcurrentHashMap<String, List<AngleAdvisor>>();

    private ApplicationContext applicationContext;

    /**
     * 把切面转为增强器
     * @return
     */
    public List<AngleAdvisor> transAdvice2Advisor() throws NoSuchMethodException {

        List<AngleAdvisor> angleAdvices = new ArrayList<AngleAdvisor>();

        if(angleAspectBeanNames.isEmpty()) {
            //第一步:去容器中查找所有的类名称
            List<String> beanNameList = Arrays.asList(getApplicationContext().getBeanNamesForType(Object.class));

            //获取list<beanName>的迭代器
            Iterator<String> nameIterator = beanNameList.iterator();

            //开始迭代
            while (nameIterator.hasNext()) {
                String beanName = nameIterator.next();
                //根据beanName获取 class对象
                Class<?> clazz = applicationContext.getType(beanName);
                //判断class对象是不是切面对象
                if(AngleAspectjFinder.isAngleAspect(clazz)){
                    angleAspectBeanNames.add(beanName);
                    //把我们的切面转为增强器
                    List<AngleAdvisor> angleAspectList =AngleAspectjFinder.getAdvisor4Aspect(clazz,applicationContext);
                    //加入到缓存中
                    advisorsCache.put(beanName,angleAspectList);
                    angleAdvices.addAll(angleAdvices);
                }
            }
            return angleAdvices;
        }else{
            //缓存中有 直接从缓存中获取
            for(String angleAspectBeanName:angleAspectBeanNames) {
                angleAdvices.addAll(advisorsCache.get(angleAspectBeanName));
            }
            return angleAdvices;

        }
    }
}
