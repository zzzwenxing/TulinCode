package com.tuling.entity;

import java.io.Serializable;

/**
 * Created by smlz on 2019/3/22.
 */
public class Dept implements Serializable{

    private Integer id;

    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
