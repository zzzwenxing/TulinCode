package bit.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BasiceTest {
    ClassPathXmlApplicationContext context;

    protected ApplicationContext getSpringContext() {
        if (context == null) {
            synchronized (this) {
                context = new ClassPathXmlApplicationContext("config/spring.xml");
            }
        }
        return context;
    }

    protected <T> T getBean(Class<T> cla) {
        return getSpringContext().getBean(cla);
    }
}