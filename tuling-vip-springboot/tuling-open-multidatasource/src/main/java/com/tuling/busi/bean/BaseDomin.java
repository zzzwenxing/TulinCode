package com.tuling.busi.bean;

import com.tuling.multidatasource.dynamicdatasource.MultiDataSourceHolder;

/**
 * Created by smlz on 2019/4/17.
 */

public class BaseDomin {

    private String tableSuffix;

    public String getTableSuffix() {
        this.tableSuffix = MultiDataSourceHolder.getTableIndex();
        return tableSuffix;
    }

    public void setTableSuffix(String tableSuffix) {
        this.tableSuffix = tableSuffix;
    }
}
