package com.macro.mall.portal.service;

import com.macro.mall.common.api.CommonResult;

/**
 *                  ,;,,;
 *                ,;;'(    社
 *      __      ,;;' ' \   会
 *   /'  '\'~~'~' \ /'\.)  主
 * ,;(      )    /  |.     义
 *,;' \    /-.,,(   ) \    码
 *     ) /       ) / )|    农
 *     ||        ||  \)     
 *     (_\       (_\
 * @author ：杨过
 * @date ：Created in 2020/1/12 19:32
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 交易服务
 **/
public interface TradeService {

    /**
     * 根据订单生成支付二维码
     * @param orderId
     * @param payType
     * @return
     */
    CommonResult tradeQrCode(Long orderId, Integer payType);

    /**
     * 查询订单支付状态
     * @param orderId
     * @return
     */
    CommonResult tradeStatusQuery(Long orderId, Integer payType);
}
