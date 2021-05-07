package com.tuling.service;

import com.tuling.dao.UserDao;
import com.tuling.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by smlz on 2019/3/26.
 */
@Service
public class UserServiceImpl {


    @Autowired
    private UserDao userDao;

    public User queryUserById(Integer userId){
        return userDao.queryUserById(userId);
    }

    public User queryUserByName(String userName) {
        return userDao.queryUserByUserName(userName);
    }
}
