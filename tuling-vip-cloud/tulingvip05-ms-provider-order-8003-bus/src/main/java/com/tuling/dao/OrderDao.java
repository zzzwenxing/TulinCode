package com.tuling.dao;

import com.tuling.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by smlz on 2019/3/26.
 */
@Mapper
public interface OrderDao {

    @Select("select * from orders where user_id=#{userId}")
    List<Order> queryOrdersByUserId(Integer userId);
}
