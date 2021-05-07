package com.macro.mall.portal.service;

import com.macro.mall.portal.domain.FlashPromotionProduct;
import com.macro.mall.portal.domain.PmsProductParam;

import java.util.List;

/**
 * @author ：杨过
 * @date ：Created in 2019/12/31
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public interface PmsProductService {
    /**
     * add by yangguo
     * 获取商品详情信息
     * @param id 产品ID
     */
    PmsProductParam getProductInfo(Long id,Long flashPromotionId,Long flashPromotionSessionId);

    /**
     * 获取秒杀商品
     * @param pageSize 页大小
     * @param pageNum 页号
     * @param flashPromotionId 秒杀活动ID，关联秒杀活动设置
     * @param sessionId 场次活动ID，for example：13:00-14:00场等
     */
    List<FlashPromotionProduct> getFlashProductList(Integer pageSize,Integer pageNum,Long flashPromotionId, Long sessionId);
}
