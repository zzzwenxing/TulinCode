package com.macro.mall.portal.dao;

import com.macro.mall.portal.domain.CartProduct;
import com.macro.mall.portal.domain.PmsProductParam;
import com.macro.mall.portal.domain.PromotionProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 前台系统自定义商品Dao
 * Created on 2018/8/2.
 */
public interface PortalProductDao {
    CartProduct getCartProduct(@Param("id") Long id);
    List<PromotionProduct> getPromotionProductList(@Param("ids") List<Long> ids);


    /**
     * add by yangguo
     * 获取商品详情信息
     * @param id 产品ID
     */
    PmsProductParam getProductInfo(@Param("id") Long id,@Param("flashPromotionId") Long flashPromotionId,@Param("flashPromotionSessionId") Long flashPromotionSessionId);
}
