package com.macro.mall.portal.service;

import com.macro.mall.model.SmsFlashPromotion;
import com.macro.mall.model.SmsFlashPromotionSession;
import com.macro.mall.portal.domain.FlashPromotionSessionExt;

import java.util.Date;
import java.util.List;

/**
 * @author ：杨过
 * @date ：Created in 2019/12/30
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 秒杀业务处理Service
 **/
public interface SmsFlashPromotionService {

    /**
     * 获取下一个场次信息
     * @param date
     *        当前时间
     * @return
     *         com.macro.mall.model.SmsFlashPromotionSession
     */
    SmsFlashPromotionSession getFlashPromotionSession(Date date);

    /**
     * 获取下一个场次信息
     * @param date
     *        当前时间
     * @return
     *          com.macro.mall.model.SmsFlashPromotionSession
     */
    SmsFlashPromotionSession getNextFlashPromotionSession(Date date);

    /**
     * 根据日期时间获取秒杀活动
     * @param date
     *        当前日期
     * @return
     *        com.macro.mall.model.SmsFlashPromotion
     */
    SmsFlashPromotion getFlashPromotion(Date date);

    /**
     * 获取当前日期秒杀活动所有场次
     */
    List<FlashPromotionSessionExt> getFlashPromotionSessionList();

}
