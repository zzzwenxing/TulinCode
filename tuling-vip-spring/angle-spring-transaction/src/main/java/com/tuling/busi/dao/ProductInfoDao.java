package com.tuling.busi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by smlz on 2019/6/23.
 */
@Repository
public class ProductInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateProductInfo(Integer productId) {
        String sql = "update product_info set store=store-1 where product_id=?";
        return jdbcTemplate.update(sql,productId);
    }

}
