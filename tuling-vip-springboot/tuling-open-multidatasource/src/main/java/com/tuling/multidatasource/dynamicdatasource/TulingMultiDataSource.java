package com.tuling.multidatasource.dynamicdatasource;


import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * 多数据源类
 * Created by smlz on 2019/4/16.
 */
@Slf4j
public class TulingMultiDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return MultiDataSourceHolder.getDataSourceKey();
    }
}
