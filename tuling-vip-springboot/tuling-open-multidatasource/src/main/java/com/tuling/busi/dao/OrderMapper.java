package com.tuling.busi.dao;

import com.tuling.busi.bean.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by smlz on 2019/4/17.
 */
public interface OrderMapper {

    void insertOrder(Order order);
}
