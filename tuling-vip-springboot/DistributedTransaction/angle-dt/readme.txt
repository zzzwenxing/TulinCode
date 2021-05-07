1:本插件主要提供的是分布式事务一种解决方案,不可商用。
2)通过springboot自定义启动器的形式提供出来,我们拿到本工程需要通过
maven install到自己的本地仓库中,然后再对应的测试工程中导入即可
		<dependency>
			<groupId>com.tuling</groupId>
			<artifactId>angle-dt-stater</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
3)比如我们现在以angle-order工程和order-product工程为演示案例
在angle-order,order-product工程中的主启动类上需要开启分布式事务支撑注解
@EnableAngleDistributedTransactional
4)比如我们的angle-order工程的service中的方法需要分布式事务方法上加入

@AngleTransactional(transType = TransactionalTypeEunm.BEGIN)
4.1)TransactionalTypeEunm.BEGIN表示分布式事务开始的子服务。
4.2)TransactionalTypeEunm.END  表示分布式事务接受的子服务。

    @Override
    @Transactional
    @AngleTransactional(transType = TransactionalTypeEunm.BEGIN)
    public void saveOrder(OrderInfo order) {
        log.info("执行目标方法");
        orderInfoMapper.saveOrder(order);

        //构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("globalTransactionId", AngleTransactionalHolder.get());
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

        //发送请求 远程调用product服务
        ResponseEntity<String> response = restTemplate.exchange(url+order.getProductId(), HttpMethod.GET, requestEntity, String.class);

        if("error".equals(response.getBody())) {
            throw new RuntimeException("调用远程服务异常"+url+order.getProductId());
        }

        System.out.println(1/0);
    }



=====================================product工厂中的service。
    @Override
    @Transactional
    @AngleTransactional(transType = TransactionalTypeEunm.END)
    public void updateProductStock(String productId) {
        productInfoMapper.updateProductInfo(productId);
    }


================================本插件不提供讲解,注释已经写的很详细。======================================