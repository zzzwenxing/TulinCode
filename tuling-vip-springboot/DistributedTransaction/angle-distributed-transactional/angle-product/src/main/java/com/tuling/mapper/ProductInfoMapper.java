package com.tuling.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * Created by smlz on 2019/7/28.
 */
@Mapper
public interface ProductInfoMapper {

    @Update("update product_stock_info set stock_num=stock_num-1 where product_id=#{productId}")
    public void updateProductInfo(String productId);
}
