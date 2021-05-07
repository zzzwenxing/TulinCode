package com.d2c.member.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d2c.member.business.model.User;
import com.d2c.member.business.service.UserService;

@RestController
public class UserRestCtrl {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public User findByName(@RequestParam(value = "username", required = true) String username) {
        return userService.findByName(username);
    }

    @RequestMapping(value = "/api/user/cache", method = RequestMethod.GET)
    public User findCacheByName(@RequestParam(value = "username", required = true) String username) {
        return userService.findCacheByName(username);
    }

    @RequestMapping(value = "/api/user/update/{id}", method = RequestMethod.GET)
    public int updatePasswdById(@PathVariable(name = "id") Long id, @RequestParam(value = "password", required = true) String password) {
        return userService.updatePasswdById(id, password);
    }

}
