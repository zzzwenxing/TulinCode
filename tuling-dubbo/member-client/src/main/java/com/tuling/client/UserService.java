package com.tuling.client;

import java.util.List;

/**
 * @author Tommy
 * Created by Tommy on 2019/12/4
 **/
public interface UserService {
    User getUser(Integer id);

    List<User> findUser(String city, String sex);
}
