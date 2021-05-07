package com.tuling.service;/**
 * Created by Administrator on 2018/8/29.
 */

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/29
 **/
public interface UserSerivce {
    public void createUser(String name);

}
