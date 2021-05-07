package com.macro.mall.portal.service;

import com.macro.mall.portal.domain.PortalMemberInfo;

/**
 * @author ：杨过
 * @date ：Created in 2020/1/6
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public interface UmsMemberCenterService {

    /**
     * 查询会员信息
     * @param memberId
     * @return
     */
    PortalMemberInfo getMemberInfo(Long memberId);
}
