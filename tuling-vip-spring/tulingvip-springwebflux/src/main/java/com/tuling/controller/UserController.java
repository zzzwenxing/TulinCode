package com.tuling.controller;

import com.tuling.bean.User;
import com.tuling.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Created by smlz on 2019/6/26.
 */
@RestController
public class UserController {

/*    @Autowired
    UserDao userDao;



    @RequestMapping("/findOneByIdWithWebFlux/{userId}")
    public Mono<User> findOneByIdWithWebFlux(@PathVariable Integer userId) {
        return userDao.findOneById(userId);
    }

    @RequestMapping("/findAllByWebFlux")
    public Flux<User> findAllByWebFlux() {
        return userDao.findAll();
    }*/
}
