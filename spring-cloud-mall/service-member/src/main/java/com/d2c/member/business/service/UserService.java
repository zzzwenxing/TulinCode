package com.d2c.member.business.service;

import com.d2c.member.business.model.User;

public interface UserService {

    User findByName(String username);

    User findCacheByName(String username);

    int updatePasswdById(Long id, String password);

}
