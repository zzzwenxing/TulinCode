package com.macro.mall.service;

import com.macro.mall.common.exception.BusinessException;
import com.macro.mall.domain.Register;
import com.macro.mall.domain.UmsMember;

/**
 * ,;,,;
 * ,;;'(    社
 * __      ,;;' ' \   会
 * /'  '\'~~'~' \ /'\.)  主
 * ,;(      )    /  |.     义
 * ,;' \    /-.,,(   ) \    码
 * ) /       ) / )|    农
 * ||        ||  \)
 * (_\       (_\
 *
 * @author ：杨过
 * @date ：Created in 2020/3/22
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public interface MemberService {

    /**
     * @return
     *  otpCode
     */
    public String getOtpCode(String telPhone) throws BusinessException;

    /**
     * 用户简单信息注册
     * @return
     */
    public int regite(Register register) throws BusinessException;

    public UmsMember login(String username, String password) throws BusinessException;
}
