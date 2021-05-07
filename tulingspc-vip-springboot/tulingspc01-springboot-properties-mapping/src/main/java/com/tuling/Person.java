package com.tuling;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by smlz on 2019/5/21.
 */
@ConfigurationProperties(prefix = "person")
public class Person {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isMarriage() {
        return isMarriage;
    }

    public void setMarriage(boolean marriage) {
        isMarriage = marriage;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<String> getHobbit() {
        return hobbit;
    }

    public void setHobbit(List<String> hobbit) {
        this.hobbit = hobbit;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    private String name;

    private Integer age;

    private double salary;

    private boolean isMarriage;

    private Car car;

    private List<String> hobbit;

    private Map<String,Object> maps;

    private Date birthDate;
}
