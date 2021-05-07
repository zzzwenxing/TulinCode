package com.macro.mall.service.impl;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.common.api.ResultCode;
import com.macro.mall.common.exception.BusinessException;
import com.macro.mall.config.properties.RedisKeyPrefixConfig;
import com.macro.mall.domain.Register;
import com.macro.mall.domain.UmsMember;
import com.macro.mall.domain.UmsMemberExample;
import com.macro.mall.mapper.UmsMemberMapper;
import com.macro.mall.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
@Slf4j
@EnableConfigurationProperties(value = RedisKeyPrefixConfig.class)
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisKeyPrefixConfig redisKeyPrefixConfig;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取动态校验码
     * @param telPhone
     * @return
     */
    @Override
    public String getOtpCode(String telPhone) throws BusinessException {
        //1、查询当前用户有么有注册
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andPhoneEqualTo(telPhone);
        List<UmsMember> reulsts = umsMemberMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(reulsts)){
            throw new BusinessException("用户已经注册!不能重复注册");
        }
        //2、校验60s后有没有再次发送
        if(redisTemplate.hasKey(redisKeyPrefixConfig.getPrefix().getOtpCode()+telPhone)){
            throw new BusinessException("请60s后再试!");
        }

        //3、生产随机校验码
        Random random = new Random();
        StringBuilder stb = new StringBuilder();

        for(int j=0;j<6;j++){
            stb.append(random.nextInt(10));
        }

        log.info("otpcode{}",stb.toString());
        redisTemplate.opsForValue().set(redisKeyPrefixConfig.getPrefix().getOtpCode()+telPhone
                ,stb.toString(),redisKeyPrefixConfig.getExpire().getOtpCode(), TimeUnit.SECONDS);

        return stb.toString();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public int regite(Register register) throws BusinessException {

        String otpCode = (String)redisTemplate.opsForValue().get(redisKeyPrefixConfig.getPrefix().getOtpCode()
        + register.getPhone());

        if(StringUtils.isEmpty(otpCode) || !otpCode.equals(register.getOtpCode())){
            throw  new BusinessException("动态校验码不正确!");
        }

        UmsMember umsMember = new UmsMember();
        umsMember.setStatus(1);
        umsMember.setMemberLevelId(4L);
        BeanUtils.copyProperties(register,umsMember);
        umsMember.setPassword(passwordEncoder.encode(register.getPassword()));
        return umsMemberMapper.insertSelective(umsMember);
    }

    @Override
    public UmsMember login(String username, String password) throws BusinessException {
        UmsMemberExample memberExample = new UmsMemberExample();
        memberExample.createCriteria().andUsernameEqualTo(username).andStatusEqualTo(1);
        List<UmsMember> result = umsMemberMapper.selectByExample(memberExample);
        if(CollectionUtils.isEmpty(result)){
            throw new BusinessException("用户名或密码不正确!");
        }
        if(result.size() > 1){
            throw new BusinessException("用户名被注册过多次,请联系客服!");
        }
        UmsMember member = result.get(0);
        if(!passwordEncoder.matches(password,member.getPassword())){
            throw new BusinessException("用户名或密码不正确!");
        }
        return member;
    }

    /*public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }*/
}
