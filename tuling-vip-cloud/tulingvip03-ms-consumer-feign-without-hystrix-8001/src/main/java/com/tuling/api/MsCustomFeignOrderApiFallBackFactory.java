package com.tuling.api;

import com.tuling.entity.OrderVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smlz on 2019/4/15.
 */
@Slf4j
@Component
public class MsCustomFeignOrderApiFallBackFactory implements FallbackFactory<MsCustomFeignOrderApi> {

    @Override
    public MsCustomFeignOrderApi create(Throwable cause) {

        return new MsCustomFeignOrderApi() {
            @Override
            public List<OrderVo> queryOrdersByUserId(Integer userId) {
                log.info("cause:{}",cause);
                log.info("通过用户ID:{}查询订单的降级方法",userId);
                List<OrderVo> orderVoList = new ArrayList<>();
                OrderVo orderVo = new OrderVo();
                orderVo.setUserId(userId);
                orderVo.setOrderId(-1);

                orderVoList.add(orderVo);
                return orderVoList;
            }

            @Override
            public List<OrderVo> queryAll() {
                log.info("queryAll的降级方法");
                List<OrderVo> orderVoList = new ArrayList<>();
                OrderVo orderVo = new OrderVo();
                orderVo.setUserId(-1);
                orderVo.setOrderId(-1);

                orderVoList.add(orderVo);
                return orderVoList;
            }
        };
    }
}
