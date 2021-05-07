package com.tuling.service.impl;

import com.tuling.bo.MsgTxtBo;
import com.tuling.entity.MessageContent;
import com.tuling.entity.ProductInfo;
import com.tuling.enumration.MsgStatusEnum;
import com.tuling.exception.BizExp;
import com.tuling.mapper.MsgContentMapper;
import com.tuling.mapper.ProductInfoMapper;
import com.tuling.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by smlz on 2019/10/13.
 */
@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Transactional
    @Override
    public boolean updateProductStore(MsgTxtBo msgTxtBo) {
        boolean updateFlag = true;
        try{
            //更新库存
            productInfoMapper.updateProductStoreById(msgTxtBo.getProductNo());
            //System.out.println(1/0);
        }catch (Exception e) {
            log.error("更新数据库失败:{}",e);
            throw new BizExp(0,"更新数据库异常");
        }
        return updateFlag;
    }
}
