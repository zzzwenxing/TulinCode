package com.tuling.api;

import com.tuling.entity.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**降级方法
 * Created by smlz on 2019/4/15.
 */
@Component
@Slf4j
public class MsCustomFeignOrderApiFallBack implements MsCustomFeignOrderApi {
    @Override
    public List<OrderVo> queryOrdersByUserId(Integer userId) {
        log.info("通过用户ID:{}查询订单的降级方法",userId);
        List<OrderVo> orderVoList = new ArrayList<>();
        OrderVo orderVo = new OrderVo();
        orderVo.setUserId(userId);
        orderVo.setOrderId(-1);

        orderVoList.add(orderVo);
        return orderVoList;
    }

    @Override
    public String info() {
        System.out.println("进入异常方法");
        return "异常IP";
    }
}
