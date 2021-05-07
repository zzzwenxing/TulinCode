package com.tuling.mapper;

import com.tuling.entity.OrderInfo;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:订单信息mapper
* @author: smlz
* @createDate: 2019/10/11 15:04
* @version: 1.0
*/
public interface OrderInfoMapper {

    /**
     * 方法实现说明:订单保存
     * @author:smlz
     * @param orderInfo:订单实体
     * @return: int 插入的条数
     * @date:2019/10/11 15:04
     */
    int saveOrderInfo(OrderInfo orderInfo);
}
