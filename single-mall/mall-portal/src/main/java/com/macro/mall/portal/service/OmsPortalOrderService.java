package com.macro.mall.portal.service;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.common.exception.BusinessException;
import com.macro.mall.portal.domain.ConfirmOrderResult;
import com.macro.mall.portal.domain.OmsOrderDetail;
import com.macro.mall.portal.domain.OrderParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 前台订单管理Service
 * Created on 2018/8/30.
 */
public interface OmsPortalOrderService {
    /**
     * 根据用户购物车信息生成确认单信息
     */
    ConfirmOrderResult generateConfirmOrder(List<Long> itemIds) throws BusinessException;

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam) throws BusinessException;

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    CommonResult getDetailOrder(Long orderId);

    /**
     * 支付成功后的回调
     */
    @Transactional
    Integer paySuccess(Long orderId,Integer payType);

    /**
     * 自动取消超时订单
     */
    @Transactional
    CommonResult cancelTimeOutOrder();

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);

    /**
     * 删除订单[逻辑删除],只能status为：3->已完成；4->已关闭；5->无效订单，才可以删除
     * ，否则只能先取消订单然后删除。
     * @param orderId
     * @return
     *      受影响的行
     */
    @Transactional
    int deleteOrder(Long orderId);
    /**
     * 发送延迟消息取消订单
     */
    void sendDelayMessageCancelOrder(Long orderId);

    /**
     * 查询会员的订单
     * @param pageSize
     * @param pageNum
     * @param memberId
     *      会员ID
     * @param status
     *      订单状态
     * @return
     */
    CommonResult<List<OmsOrderDetail>> findMemberOrderList(Integer pageSize,Integer pageNum,Long memberId, Integer status);
}
