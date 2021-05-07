package com.macro.mall.controller;

import com.macro.mall.util.JwtKit;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.common.exception.BusinessException;
import com.macro.mall.config.properties.JwtProperties;
import com.macro.mall.domain.Register;
import com.macro.mall.domain.UmsMember;
import com.macro.mall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ：杨过
 * @date ：Created in 2020/3/22
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
@RestController
@RequestMapping("/sso")
public class MemberController extends BaseController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtKit jwtKit;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/getOtpCode")
    public CommonResult getOtpCode(@RequestParam String telPhone) throws BusinessException {
        String otpCode = memberService.getOtpCode(telPhone);
        return CommonResult.success(otpCode);
    }

    @PostMapping("/registe")
    public CommonResult regite(@Valid @RequestBody Register register) throws BusinessException {
        int result = memberService.regite(register);
        if(result > 0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @PostMapping("/login")
    public CommonResult login(@RequestParam String username,@RequestParam String password) throws BusinessException {
        UmsMember member = memberService.login(username,password);
        if(member!=null){
            getHttpSession().setAttribute("member", member);
            getHttpSession().getAttribute("member");//redisSession
            return CommonResult.success(username + "登录成功!");
        }
        return CommonResult.failed();
    }

    @PostMapping("/jwt_login")
    public CommonResult jwtLogin(@RequestParam String username,@RequestParam String password) throws BusinessException {
        UmsMember member = memberService.login(username,password);
        if(member!=null){
            Map<String,String> map = new HashMap<>();
            String token = jwtKit.generateJwtToken(member);
            map.put("tokenHead",jwtProperties.getTokenHead());
            map.put("token",token);
            return CommonResult.success(map);
        }
        return CommonResult.failed();
    }

}
