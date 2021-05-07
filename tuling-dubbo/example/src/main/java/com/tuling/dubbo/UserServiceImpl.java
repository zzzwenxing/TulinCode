package com.tuling.dubbo;

import com.tuling.client.User;
import com.tuling.client.UserService;
import org.springframework.beans.factory.BeanFactory;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tommy
 * Created by Tommy on 2019/12/4
 **/
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("luban:" + ManagementFactory.getRuntimeMXBean().getName());
        user.setSex("man");
        return user;
    }

    @Override
    public List<User> findUser(String city, String sex) {
        return Arrays.asList(getUser(1));
    }
}
