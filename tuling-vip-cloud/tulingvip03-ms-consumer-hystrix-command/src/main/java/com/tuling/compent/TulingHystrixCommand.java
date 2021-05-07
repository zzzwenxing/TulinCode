package com.tuling.compent;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.tuling.entity.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by smlz on 2019/4/12.
 */
@Slf4j
public class TulingHystrixCommand extends HystrixCommand<List<OrderVo>> {

    private Integer userId;

    private RestTemplate restTemplate;

    @Override
    protected List<OrderVo> run() throws Exception {
        ResponseEntity<List> responseEntity  =  restTemplate.getForEntity("http://MS-PROVIDER-ORDER/order/queryOrdersByUserId/"+userId, List.class);
        List<OrderVo> orderVoList = responseEntity.getBody();
        log.info("查询出的orderVoList:{}",orderVoList);
        return orderVoList;
    }

    /**
     * 降级方法(回退)
     * @return
     */
    @Override
    protected List<OrderVo> getFallback() {
        log.info("触发降级方法========================>");
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderId(-1);
        orderVo.setUserId(-1);
        orderVo.setOrderMoney(new BigDecimal(0));

        List<OrderVo> orderVos = new ArrayList<>();
        orderVos.add(orderVo);
        return orderVos;
    }

    //构造方法
    public TulingHystrixCommand(String commandGroupKey, RestTemplate restTemplate, Integer userId) {
        super(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        this.restTemplate = restTemplate;
        this.userId = userId;
    }
}
