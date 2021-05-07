package com.tuling.mybatis.service.impl;

import com.tuling.mybatis.dao.User;
import com.tuling.mybatis.dao.UserMapper;
import com.tuling.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Tommy
 * Created by Tommy on 2019/7/4
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(Integer id) {
        userMapper.selectByid(id);
        return userMapper.selectByid(id);
    }

    @Override
    @Transactional()
    public User getUser2(Integer id) {
        userMapper.selectByid(id);
        return userMapper.selectByid(id);
    }

    @Override
    @Transactional()
    public void registerUser(User user) {
        userMapper.insertUser(user);
        int i = 1 / 0;
    }
}
