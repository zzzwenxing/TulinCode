package com.tuling.mybatis.service;

import com.tuling.mybatis.dao.User;

/**
 * @author Tommy
 * Created by Tommy on 2019/7/4
 **/
public interface UserService {
    User getUser(Integer id);

    User getUser2(Integer id);

    void registerUser(User user);
}
