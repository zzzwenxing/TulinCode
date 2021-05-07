package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.portal.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：杨过
 * @date ：Created in 2019/12/31
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 商品详情信息管理
 **/
@RestController
@Api(tags = "PortalProductController", description = "商品查询查看#杨过添加")
@RequestMapping("/pms")
public class PortalProductController {

    @Autowired
    private PmsProductService pmsProductService;

    @ApiOperation(value = "根据商品id获取商品详情#功能需要做QPS优化")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "flashPromotionId",value = "秒杀活动ID",paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "flashPromotionSessionId",value = "活动场次ID,例如:12点场",paramType = "query",dataType = "long")
    })
    @RequestMapping(value = "/productInfo/{id}", method = RequestMethod.GET)
    public CommonResult getProductInfo(@PathVariable Long id,
           //当前秒杀活动主题ID
           Long flashPromotionId,
           //当前秒杀活动场次ID
           Long flashPromotionSessionId) {
        return CommonResult.success(pmsProductService.getProductInfo(id,flashPromotionId,flashPromotionSessionId));
    }

}
