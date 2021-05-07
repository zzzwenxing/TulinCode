package com.tuling.testautowired.autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/5/24.
 */
public class TulingMainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TulingMainConfig.class);
        //TulingService tulingService = ctx.getBean(TulingService.class);
        //System.out.println(tulingService.toString());

        //Object tulingDao = ctx.getBean("tulingDao2");
        //System.out.println(tulingDao.toString());

        //测试@AutoWired使用的时byName还是byType(默认是使用byType,当发现多个类型的bean话 就通过byName)
        //需要指定装配的名称通过@Qualifier指定名称装配
        BaiDuService baiDuService = ctx.getBean(BaiDuService.class);
        System.out.println(baiDuService);
    }
}
