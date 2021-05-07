package com.tuling.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by smlz on 2019/3/26.
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer orderId;

    private BigDecimal orderMoney;

    private Integer userId;

    private Date createDt;

}
