package com.zhuge.order.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhuge.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;

    /**
     * 基于Feign调用服务接口
     *
     * @param productId
     * @param userId
     * @param stockCount
     * @param creditCount
     * @return
     */
    @GetMapping(value = "/create")
    public String createOrder(
            @RequestParam("productId") Long productId,
            @RequestParam("userId") Long userId,
            @RequestParam("stockCount") Integer stockCount,
            @RequestParam("creditCount") Integer creditCount) {
        orderService.createOrder(productId, userId, stockCount, creditCount);
        return "success";
    }

    /**
     * 直接调用原生http接口
     *
     * @param productId
     * @param stockCount
     * @return
     */
    @GetMapping("/stock/deduct")
    public String deductStock(@RequestParam("productId") Long productId, @RequestParam("stockCount") Long stockCount) {
        return this.restTemplate.getForObject("http://localhost:9001/stock/deduct/" + productId + "/" + stockCount,
                String.class);
    }

    /**
     * 基于Ribbon调用服务接口
     *
     * @return
     */
    @GetMapping("/stock/getIpAndPort")
    public String getIpAndPort() {
        return this.restTemplate.getForObject("http://stock-feign/stock/getIpAndPort", String.class);
    }


    @GetMapping("/test-sentinel-api")
    public String testSentinelAPI() {
        String resourceName = "/order/create";
        Entry entry = null;
        try {
            // 定义一个sentinel保护的资源，名称为test-sentinel-api
            entry = SphU.entry(resourceName);
            // 模拟执行被保护的业务逻辑耗时
            Thread.sleep(100);
            return "success";
        } catch (BlockException e) {
            // 如果被保护的资源被限流或者降级了，就会抛出BlockException
            return "资源被限流或降级了";
        } catch (InterruptedException e) {
            // 对业务异常进行统计
            Tracer.trace(e);
            return "发生业务异常";
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }


    @GetMapping("/test-sentinel-resource")
    @SentinelResource(value = "/order/create",
            blockHandler = "blockHandlerFunc",
            fallback = "fallbackFunc")
    public String testSentinelResource(@RequestParam(required = false) String a)
            throws InterruptedException {
        // 模拟执行被保护的业务逻辑耗时
        Thread.sleep(100);
        return a;
    }

    /**
     * 处理BlockException的函数（处理限流）
     */
    public String blockHandlerFunc(String a, BlockException e) {
        // 如果被保护的资源被限流或者降级了，就会抛出BlockException
        return "资源被限流或降级了";
    }

    public String fallbackFunc(String a) {
        return "发生异常了";
    }

}
