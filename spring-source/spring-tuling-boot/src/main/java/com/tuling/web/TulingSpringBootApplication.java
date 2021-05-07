package com.tuling.web;

import com.tuling.config.TulingSpringbootConfig;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Created by smlz on 2019/8/18.
 */
public class TulingSpringBootApplication {

    public static void run() {

        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);

        try {
            tomcat.addWebapp("/","D:\\");

            tomcat.start();

            tomcat.getServer().await();
        }catch (Exception e) {
			System.out.println("容器启动失败");
		}
    }

}
