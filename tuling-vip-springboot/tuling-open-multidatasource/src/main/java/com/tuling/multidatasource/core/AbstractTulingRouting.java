package com.tuling.multidatasource.core;

import com.tuling.multidatasource.constant.TulingConstant;
import com.tuling.multidatasource.enumuration.MultiDsErrorEnum;
import com.tuling.multidatasource.exception.FormatTableSuffixException;
import com.tuling.multidatasource.exception.LoadRoutingStategyUnMatch;
import com.tuling.multidatasource.support.TulingDsRoutingSetProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
 * 路由规则抽象类
 * Created by smlz on 2019/4/16.
 */
@Slf4j
@EnableConfigurationProperties(value = {TulingDsRoutingSetProperties.class})
@Data
public abstract class AbstractTulingRouting implements ITulingRouting ,InitializingBean{

    @Autowired
    private TulingDsRoutingSetProperties tulingDsRoutingSetProperties;

    /**
     * 获取路由key的hash值
     * @param routingFiled 路由key
     * @return
     */
    public Integer getRoutingFileHashCode(String routingFiled){
        return Math.abs(routingFiled.hashCode());
    }

    /**
     * 获取表的后缀
     * @param tableIndex 表的索引值
     * @return
     */
    public String getFormatTableSuffix(Integer tableIndex) {
        StringBuffer stringBuffer = new StringBuffer(tulingDsRoutingSetProperties.getTableSuffixConnect());

        try {
            stringBuffer.append(String.format(getTulingDsRoutingSetProperties().getTableSuffixStyle(), tableIndex));
        } catch (Exception e) {
            log.error("格式化表后缀异常:{}",getTulingDsRoutingSetProperties().getTableSuffixStyle());
            throw new FormatTableSuffixException(MultiDsErrorEnum.FORMAT_TABLE_SUFFIX_ERROR);
        }
        return stringBuffer.toString();
    }


    /**
     * 工程在启动的时候 检查配置路由参数和 策略是否相匹配
     * @throws Exception
     */
    public void afterPropertiesSet() throws LoadRoutingStategyUnMatch{

        switch (getTulingDsRoutingSetProperties().getRoutingStategy()) {
            case TulingConstant.ROUTING_DS_TABLE_STATEGY:
                checkRoutingDsTableStategyConfig();
                break;
            case TulingConstant.ROUTGING_DS_STATEGY:
                checkRoutingDsStategyConfig();
                break;
            case TulingConstant.ROUTGIN_TABLE_STATEGY:
                checkRoutingTableStategyConfig();
                break;
        }
    }

    /**
     * 检查多库 多表配置
     */
    private void checkRoutingDsTableStategyConfig() {
        if(tulingDsRoutingSetProperties.getTableNum()<=1 ||tulingDsRoutingSetProperties.getDataSourceNum()<=1){
            log.error("你的配置项routingStategy:{}是多库多表配置,数据库个数>1," +
                    "每一个库中表的个数必须>1,您的配置:数据库个数:{},表的个数:{}",tulingDsRoutingSetProperties.getRoutingStategy(),
                    tulingDsRoutingSetProperties.getDataSourceNum(),tulingDsRoutingSetProperties.getTableNum());
            throw new LoadRoutingStategyUnMatch(MultiDsErrorEnum.LOADING_STATEGY_UN_MATCH);
        }
    }

    /**
     * 检查多库一表的路由配置项
     */
    private void checkRoutingDsStategyConfig() {
        if(tulingDsRoutingSetProperties.getTableNum()!=1 ||tulingDsRoutingSetProperties.getDataSourceNum()<=1){
            log.error("你的配置项routingStategy:{}是多库一表配置,数据库个数>1," +
                            "每一个库中表的个数必须=1,您的配置:数据库个数:{},表的个数:{}",tulingDsRoutingSetProperties.getRoutingStategy(),
                    tulingDsRoutingSetProperties.getDataSourceNum(),tulingDsRoutingSetProperties.getTableNum());
            throw new LoadRoutingStategyUnMatch(MultiDsErrorEnum.LOADING_STATEGY_UN_MATCH);
        }
    }

    /**
     * 检查一库多表的路由配置项
     */
    private void checkRoutingTableStategyConfig() {
        if(tulingDsRoutingSetProperties.getTableNum()<=1 ||tulingDsRoutingSetProperties.getDataSourceNum()!=1){
            log.error("你的配置项routingStategy:{}是一库多表配置,数据库个数=1," +
                            "每一个库中表的个数必须>1,您的配置:数据库个数:{},表的个数:{}",tulingDsRoutingSetProperties.getRoutingStategy(),
                    tulingDsRoutingSetProperties.getDataSourceNum(),tulingDsRoutingSetProperties.getTableNum());
            throw new LoadRoutingStategyUnMatch(MultiDsErrorEnum.LOADING_STATEGY_UN_MATCH);
        }
    }

}
