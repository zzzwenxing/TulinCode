package com.tuling.api;

import com.tuling.config.MsCustomeFeignApiWithoutHystrixConfg;
import com.tuling.entity.OrderVo;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by smlz on 2019/4/10.
 */
//@FeignClient(name = "MS-PROVIDER-ORDER",fallback =MsCustomFeignOrderApiFallBack.class ,path = "/order")
@FeignClient(name = "MS-PROVIDER-ORDER",configuration = MsCustomeFeignApiWithoutHystrixConfg.class,fallbackFactory =MsCustomFeignOrderApiFallBackFactory.class ,path = "/order")
//@FeignClient(name = "MS-PROVIDER-ORDER",fallbackFactory =MsCustomFeignOrderApiFallBackFactory.class ,path = "/order")
public interface MsCustomFeignOrderApi {

    @RequestMapping("/queryOrdersByUserId/{userId}")
    public List<OrderVo> queryOrdersByUserId(@PathVariable("userId") Integer userId);

    @RequestMapping("/queryAll")
    public List<OrderVo> queryAll();
}
