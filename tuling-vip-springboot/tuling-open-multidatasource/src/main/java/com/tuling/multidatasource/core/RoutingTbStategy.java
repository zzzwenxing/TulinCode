package com.tuling.multidatasource.core;

import com.tuling.multidatasource.dynamicdatasource.MultiDataSourceHolder;

/**
 * 一库多表策略
 * Created by smlz on 2019/4/17.
 */
public class RoutingTbStategy extends AbstractTulingRouting {

    private static final String  ROUTING_DS_STATEGY_TBALESUFFIX = "dataSource00";


    @Override
    public String calDataSourceKey(String routingFiled) {
        MultiDataSourceHolder.setdataSourceKey(ROUTING_DS_STATEGY_TBALESUFFIX);
        return ROUTING_DS_STATEGY_TBALESUFFIX;
    }

    @Override
    public String calTableKey(String routingFiled) {
        //前置检查
        Integer routingFiledHashCode =  getRoutingFileHashCode(routingFiled);

        Integer tbIndex = routingFiledHashCode % getTulingDsRoutingSetProperties().getTableNum();

        String tableSuffix = getFormatTableSuffix(tbIndex);

        MultiDataSourceHolder.setTableIndex(tableSuffix);

        return tableSuffix;
    }
}
