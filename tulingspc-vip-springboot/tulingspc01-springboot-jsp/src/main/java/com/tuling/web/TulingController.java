package com.tuling.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by smlz on 2019/5/20.
 */
@RestController
public class TulingController {

    @RequestMapping("/testBoot4Jsp")
    public String testQksByIdea() {
        return "hello";
    }

    @RequestMapping("/users/addUsers")
    public Users addUser(HttpServletRequest httpServletRequest,Model model, Users users) {
        System.out.println(users);
        return users;
    }
}
