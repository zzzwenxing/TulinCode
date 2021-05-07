package com.tuling.core;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuling.support.enumaration.TransactionalTypeEunm;
import com.tuling.support.enumaration.TransationalEnumStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

/**
* @vlog: 高于生活，源于生活
* @desc: 分布式事务管理器
* @author: smlz
* @createDate: 2019/7/26 20:57
* @version: 1.0
*/
@Slf4j
public class AngleGlobalTransactionManager {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 全局事务和子事务映射关系
     */
    public static final String GLOBAL_TRANSACTION_MAPPING_CHILDE_TRANSACTIONAL_CACHE_KEY = "ANGLE_DIST_TRANSACTIONAL:";


    /**
     * 方法实现说明:计算各个子事务是否能够被提交
     * 那么需要计算分布式事务 是提交还是回滚
     * @author:smlz
     * @param 子事务对象
     * @exception: 可能抛出redis操作存储异常
     * @date:2019/7/26 21:18
     */
    public Integer calChildTransactionStatus(String globalTransactionId) {

        //获取hashKey 下的所有filed value
        Map<String,String> childTransMap =redisTemplate.opsForHash().entries(generatorHashKey(globalTransactionId));
        Set<String> childTransIdSet = childTransMap.keySet();
        Iterator<String> iterator = childTransIdSet.iterator();

        //用户统计各个子事务commit的提交个数
        Integer commitCount =0;

        boolean needRoolBack = false;

        Integer beginAndEnd =0;

        while (iterator.hasNext()) {
            String childTransId = iterator.next();
            log.info("jsonObjectStr:{}",childTransMap.get(childTransId));
            ChildTransactionObj childTransactionObj = JSONObject.parseObject(childTransMap.get(childTransId),ChildTransactionObj.class);

            //只要子事务其中一个出现rollback,分布式事务回滚
            if(childTransactionObj.getTransationalEnumStatusCode() == TransationalEnumStatus.ROLLBACK.getCode()) {
                needRoolBack = true;
            }
            //统计commit的个数
            if(childTransactionObj.getTransationalEnumStatusCode() == TransationalEnumStatus.COMMIT.getCode()) {
                commitCount++;
            }

            if(childTransactionObj.getTransactionalTypeEunmCode() == TransactionalTypeEunm.BEGIN.getCode()||
                    childTransactionObj.getTransactionalTypeEunmCode() == TransactionalTypeEunm.END.getCode()) {
                beginAndEnd++;
            }

        }

        //没有收到begin 和end 的二个子事务
        if(beginAndEnd!=2) {
            return TransationalEnumStatus.WATING.getCode();
        }

        //收到了begin 和end的子事务对象，但是其中有出现了rollabck,全局回滚
        if(needRoolBack) {
            return TransationalEnumStatus.ROLLBACK.getCode();
        }

        //若所有的子事务都是commit的
        if(childTransMap.size() == commitCount) {
            return TransationalEnumStatus.COMMIT.getCode();
        }

        return TransationalEnumStatus.WATING.getCode();
    }




    /**
     * 方法实现说明:把子事务对象保存到redis
     * @author:smlz
     * @param ChildTransactionObj 子事务对象
     * @exception: 可能抛出redis操作存储异常
     * @date:2019/7/26 21:16
     */
    public void save2Redis(ChildTransactionObj childTransactionObj) throws Exception{

        //从子事务中获取全局事务ID
        String globalTransationalId = childTransactionObj.getGlobalTransactionalId();

        //把事务对象保存到redis中
        redisTemplate.opsForHash().put(generatorHashKey(globalTransationalId),childTransactionObj.getChildTransactionalId(),JSONObject.toJSON(childTransactionObj).toString());

    }

    /**
     * 方法实现说明
     * @author:smlz
     * @param globalTransactionId 全局事务ID
     * @return: redist hash结构的的   hashKey
     * @date:2019/7/29 21:29
     */
    private String generatorHashKey(String globalTransactionId) {
        return GLOBAL_TRANSACTION_MAPPING_CHILDE_TRANSACTIONAL_CACHE_KEY+globalTransactionId;
    }


}
