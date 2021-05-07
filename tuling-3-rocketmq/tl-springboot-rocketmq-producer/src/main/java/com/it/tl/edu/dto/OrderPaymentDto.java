package com.it.tl.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ：图灵-杨过
 * @date：2019/11/4
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description : 如果报错，需要安装lombok插件
 */
@AllArgsConstructor
@Data
public class OrderPaymentDto {
    private long orderid;

    private BigDecimal paymoney;
}
