package com.tuling.multidatasource.support;

import com.tuling.multidatasource.constant.TulingConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 该类用于配置分库的个数  以及分表的个数,以及配置路由key
 * Created by smlz on 2019/4/16.
 */
@ConfigurationProperties(prefix = "tuling.dsroutingset")
@Data
public class TulingDsRoutingSetProperties  {

    /**
     * 默认是一个数据库 默认一个
     */
    private Integer dataSourceNum = 1;

    /**
     * 每一个库对应表的个数 默认是一个
     */
    private Integer tableNum = 1;

    /**
     * 路由字段 必须在配置文件中配置(不配置会抛出异常)
     */
    private String routingFiled;

    /**
     * 所有生产写库数据有的名称
     */
    private Map<Integer,String> dataSourceKeysMapping;

    /**
     * 表的后缀连接风格 比如order_
     */
    private String tableSuffixConnect="_";

    /**
     * 表的索引值 格式化为四位 不足左补零   1->0001 然后在根据tableSuffixConnect属性拼接成
     * 成一个完整的表名  比如 order表 所以为1  那么数据库表明为 order_0001
     */
    private String tableSuffixStyle= "%04d";


    /**
     * 默认的路由策略
     */
    private String routingStategy= TulingConstant.ROUTING_DS_TABLE_STATEGY;

}
