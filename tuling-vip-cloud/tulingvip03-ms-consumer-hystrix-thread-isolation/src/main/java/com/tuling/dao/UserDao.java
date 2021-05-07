package com.tuling.dao;

import com.tuling.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by smlz on 2019/3/26.
 */
@Mapper
public interface UserDao {

    @Select("select * from user where user_id=#{userId}")
    User queryUserById(Integer userId);

    @Select("select * from user where user_name=#{userName}")
    User queryUserByUserName(String userName);
}
