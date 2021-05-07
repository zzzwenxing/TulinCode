package com.macro.mall.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.portal.dao.FlashPromotionProductDao;
import com.macro.mall.portal.dao.PortalProductDao;
import com.macro.mall.portal.domain.FlashPromotionProduct;
import com.macro.mall.portal.domain.PmsProductParam;
import com.macro.mall.portal.service.PmsProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
 * @date ：Created in 2019/12/31 17:22
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 
 **/
@Slf4j
@Service
public class PmsProductServiceImpl implements PmsProductService {

    @Autowired
    private PortalProductDao portalProductDao;

    @Autowired
    private FlashPromotionProductDao flashPromotionProductDao;

    /**
     * add by yangguo
     * 获取商品详情信息
     * @param id 产品ID
     */
    @Override
    public PmsProductParam getProductInfo(Long id,Long flashPromotionId,Long flashPromotionSessionId){
        return portalProductDao.getProductInfo(id,flashPromotionId,flashPromotionSessionId);
    }

    /**
     * add by yangguo
     * 获取秒杀商品列表
     * @param flashPromotionId 秒杀活动ID，关联秒杀活动设置
     * @param sessionId 场次活动ID，for example：13:00-14:00场等
     */
    @Override
    public List<FlashPromotionProduct> getFlashProductList(Integer pageSize,Integer pageNum,Long flashPromotionId, Long sessionId){
        PageHelper.startPage(pageNum,pageSize,"sort desc");
        return flashPromotionProductDao.getFlashProductList(flashPromotionId,sessionId);
    }
}
