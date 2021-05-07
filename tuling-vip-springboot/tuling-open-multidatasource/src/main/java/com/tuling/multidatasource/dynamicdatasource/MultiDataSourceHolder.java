package com.tuling.multidatasource.dynamicdatasource;

import lombok.extern.slf4j.Slf4j;

/**
 * 多数据源key 缓存类
 * Created by smlz on 2019/4/16.
 */
@Slf4j
public class MultiDataSourceHolder {

    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    private static final ThreadLocal<String> tableIndexHolder = new ThreadLocal<>();

    /**
     * 保存数据源的key
     * @param dsKey
     */
    public static void setdataSourceKey(String dsKey) {
        dataSourceHolder.set(dsKey);
    }

    /**
     * 从threadLocal中取出key
     * @return
     */
    public static String getDataSourceKey() {
        return dataSourceHolder.get();
    }

    /**
     * 清除key
     */
    public static void clearDataSourceKey() {
        dataSourceHolder.remove();
    }

    public static String getTableIndex(){
        return tableIndexHolder.get();
    }

    public static void setTableIndex(String tableIndex){
         tableIndexHolder.set(tableIndex);
    }

    public static void clearTableIndex(){
        tableIndexHolder.remove();
    }
}
