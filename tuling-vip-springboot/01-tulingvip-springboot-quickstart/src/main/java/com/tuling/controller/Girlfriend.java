package com.tuling.controller;

/**
 * Created by smlz on 2019/3/29.
 */
public class Girlfriend {

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Girlfriend{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
