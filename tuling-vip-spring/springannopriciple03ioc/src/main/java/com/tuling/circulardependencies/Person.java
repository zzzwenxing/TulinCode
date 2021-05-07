package com.tuling.circulardependencies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2019/5/29.
 */
@Component
public class Person {

    @Value("zhangsan")
    private String name;

    @Value("18")
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
