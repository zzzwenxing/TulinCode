package com.tuling.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by smlz on 2019/3/29.
 */
@ConfigurationProperties(prefix = "person")
public class Person {

    private String name;

    private Integer age;

    private Date birthDate;

    private List<String> pets;

    private List<String> cars;

    private Map<String,Object> maps;

    private Girlfriend girlfriend;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getPets() {
        return pets;
    }

    public void setPets(List<String> pets) {
        this.pets = pets;
    }

    public List<String> getCars() {
        return cars;
    }

    public void setCars(List<String> cars) {
        this.cars = cars;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public Girlfriend getGirlfriend() {
        return girlfriend;
    }

    public void setGirlfriend(Girlfriend girlfriend) {
        this.girlfriend = girlfriend;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                ", pets=" + pets +
                ", cars=" + cars +
                ", maps=" + maps +
                ", girlfriend=" + girlfriend +
                '}';
    }
}