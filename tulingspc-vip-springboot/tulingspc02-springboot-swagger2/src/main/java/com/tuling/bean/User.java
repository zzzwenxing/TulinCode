package com.tuling.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by smlz on 2019/3/24.
 */
@ApiModel(value = "用户返回结果")
public class User {

    @JsonIgnore
    @ApiModelProperty(name = "用户车俩",dataType = "Car" ,notes = "用户车俩")
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User(Integer userId, String userName, String password, String email, String birthDate) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @ApiModelProperty(name = "用户id",dataType = "String" ,notes = "用户Id")
    private Integer userId;

    @ApiModelProperty(name = "用户名",dataType = "String" ,notes="用户名")
    private String userName;
    @JsonIgnore
    @ApiModelProperty(name = "密码",dataType = "String",notes = "密码")
    private String password;
    @ApiModelProperty(name = "邮箱",dataType = "String" ,notes = "邮箱")
    private String email;
    @ApiModelProperty(name = "生日",dataType = "String" ,notes = "生日")
    private String birthDate;
}