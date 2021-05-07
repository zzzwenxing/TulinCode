package com.d2c.member.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.member.business.mapper.UserMapper;
import com.d2c.member.business.model.User;
import com.d2c.member.business.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User findByName(String username) {
        User user = userMapper.findByName(username);
        redisTemplate.opsForValue().set("User::user:" + username, user);
        return user;
    }

    @Override
    @Cacheable(value = "User", key = "'user:'+#username", unless = "#result == null")
    public User findCacheByName(String username) {
        return null;
    }

    @Override
    @Transactional
    public int updatePasswdById(Long id, String password) {
        return userMapper.updatePasswdById(id, password);
    }

}
