package com.tuling.busi.mapper;

import com.tuling.busi.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by smlz on 2019/7/25.
 */
@Mapper
public interface OrderInfoMapper {

    /**
     * 保存订单信息
     * @param order
     */
    void saveOrder(OrderInfo order);
}
