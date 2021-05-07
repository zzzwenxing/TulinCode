package com.tuling.mybatis.dao;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Tommy
 * Created by Tommy on 2019/6/27
 **/
@ToString
public class User implements java.io.Serializable {
    private Integer id;
    private String name;
    private Date createTime;
    private Date updateTime;
    private Long updateLongTime;

    public User() {
    }

    public User(String name, Date createTime, Date updateTime) {
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateLongTime() {
        return updateLongTime;
    }

    public void setUpdateLongTime(Long updateLongTime) {
        this.updateLongTime = updateLongTime;
    }
}
