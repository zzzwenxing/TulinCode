package com.tuling.setInject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by smlz on 2019/8/22.
 */
public class MainClass {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
		//System.out.println(ctx.getBean(InstA.class));
		//InstD instD = ctx.getBean(InstD.class);
		InstA instA = (InstA) ctx.getBean("instA");
		//InstD instD = ctx.getBean(InstD.class);
		System.out.println(instA);
		//ctx.getBean(InstD.class);

	}
}
