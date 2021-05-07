package com.macro.mall.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.common.collect.Maps;
import com.macro.mall.alipay.config.Configs;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.common.exception.BusinessException;
import com.macro.mall.domain.OrderDetail;
import com.macro.mall.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

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
 * @date ：Created in 2020/3/26 16:40
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 
 **/
@Slf4j
@RestController
@RequestMapping("/order")
public class MallOrderController extends BaseController {

    @Autowired
    private TradeService tradeService;

    /**
     * @param orderDetail
     * @return
     * @throws BusinessException
     */
    @PostMapping("/qrCode")
    public CommonResult genQrCode(@RequestBody OrderDetail orderDetail) throws BusinessException{
        String path = tradeService.tradeQrCode(orderDetail);
        if(StringUtils.isNotEmpty(path)){
            return  CommonResult.success(path);
        }
        return CommonResult.failed();
    }

    @PostMapping("/queryPayStatus")
    public CommonResult queryPayStatus(String orderSn) throws BusinessException {
        try {
            String result  = tradeService.alipayTradeQuery(orderSn);
            if(StringUtils.isNotEmpty(result)){
                return CommonResult.success(result);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(),e.getCause());
        }
        return CommonResult.failed();
    }

    @PostMapping("/paySuccess")
    public void paySuccess() throws BusinessException {
        log.info("支付宝开始回调.callback");
        Map<String,String> map = Maps.newHashMap();
        Enumeration<String> parameterNames = getRequest().getParameterNames();

        while (parameterNames.hasMoreElements()){
            String parameter = parameterNames.nextElement();
            log.info("parametername:{},value:{}",parameter,getRequest().getParameter(parameter));
            if(!parameter.toLowerCase().equals("sign_type")){
                map.put(parameter,getRequest().getParameter(parameter));
            }
        }

        try {
            boolean result = AlipaySignature.rsaCheckV2(map,Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());

            try {
                PrintWriter pw = getResponse().getWriter();
                if(result){
                    pw.print("success");
                }else{
                    pw.print("unSuccess");
                }
            } catch (IOException e) {
                throw new BusinessException(e.getMessage(),e.getCause());
            }

        } catch (AlipayApiException e) {
            throw new BusinessException(e.getMessage(),e.getCause());
        }
    }
}
