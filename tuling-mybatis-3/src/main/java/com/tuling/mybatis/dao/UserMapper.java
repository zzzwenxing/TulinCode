package com.tuling.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where id=#{id}")
    User selectByid(Integer id);

    User selectByid2(Integer id);

    int updateByid2(User user);

    int insertUser(User user);

    List<User> selectIds(@Param("ids") List<Integer> ids);

}
