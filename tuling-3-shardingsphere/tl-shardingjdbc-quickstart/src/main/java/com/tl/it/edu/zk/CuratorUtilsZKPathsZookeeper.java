package com.tl.it.edu.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;

/**
 *                  ,;,,;
 *                ,;;'(    社
 *      __      ,;;' ' \   会
 *   /'  '\'~~'~' \ /'\.)  主
 * ,;(      )    /  |.     义
 *,;' \    /-.,,(   ) \    码
 *     ) /       ) / )|    农
 *     ||        ||  \)     
 *     (_\       (_\
 * @author ：杨过
 * @date ：Created in 2019/11/26 20:04
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 
 **/
public class CuratorUtilsZKPathsZookeeper {
    private static final int SECOND = 1000;
    private static final String namespace = "/tl-zookeeper-proxy";
    private static final String path = "/orchestration-proxy/config/authentication";

    public static void main(String[] args) throws Exception {
        // 获取Curator客户端
        CuratorFramework client = new CuratorUtilsZKPathsZookeeper().getStartedClient();
        // 获取Zookeeper客户端
        ZooKeeper zk = client.getZookeeperClient().getZooKeeper();

        //client.getData().forPath(namespace + path);

        //创建根节点，namespace


        //System.out.println("节点value:" + new String(zk.getData(namespace + path,null,null)));

        /*String node = namespace + path;
        String value = "users:\n" +
                "    root:\n" +
                "      password: root\n" +
                "    tuling:\n" +
                "      password: tuling\n" +
                "      authorizedSchemas: shop_ds_proxy";*/
        //ZKPaths.mkdirs(zk,node);
        //zk.setData(node,value.getBytes(),0);

        String dataNode = "/tl-zookeeper-proxy/orchestration-proxy/config/schema/shop_ds_proxy/datasource";
        String nodeValue = "ds_0: !!org.apache.shardingsphere.orchestration.yaml.config.YamlDataSourceConfiguration\n" +
                "  dataSourceClassName: com.zaxxer.hikari.HikariDataSource\n" +
                "  properties:\n" +
                "    url: jdbc:mysql://192.168.241.198:3306/shop_ds_0?serverTimezone=UTC&useSSL=false\n" +
                "    username: root\n" +
                "    password: root\n" +
                "    connectionTimeoutMilliseconds: 30000\n" +
                "    idleTimeoutMilliseconds: 60000\n" +
                "    maxLifetimeMilliseconds: 1800000\n" +
                "    maxPoolSize: 50\n" +
                "    minPoolSize: 1\n" +
                "    maintenanceIntervalMilliseconds: 30000\n" +
                "    readOnly: false\n" +
                "ds_1: !!org.apache.shardingsphere.orchestration.yaml.config.YamlDataSourceConfiguration\n" +
                "  dataSourceClassName: com.zaxxer.hikari.HikariDataSource\n" +
                "  properties:\n" +
                "    url: jdbc:mysql://192.168.241.198:3306/shop_ds_2?serverTimezone=UTC&useSSL=false\n" +
                "    username: root\n" +
                "    password: root\n" +
                "    connectionTimeoutMilliseconds: 30000\n" +
                "    idleTimeoutMilliseconds: 60000\n" +
                "    maxLifetimeMilliseconds: 1800000\n" +
                "    maxPoolSize: 50\n" +
                "    minPoolSize: 1\n" +
                "    maintenanceIntervalMilliseconds: 30000\n" +
                "    readOnly: false";

        zk.setData(dataNode,nodeValue.getBytes(),0);

        //System.out.println("path:"+ZKPaths.fixForNamespace(namespace, path,false));

        client.close();
        System.out.println("Server closed...");
    }



    private CuratorFramework getStartedClient() {
        RetryPolicy rp = new ExponentialBackoffRetry(1 * SECOND, 3);
        // Fluent风格创建
        CuratorFramework cfFluent = CuratorFrameworkFactory.builder().connectString("192.168.241.198:2181")
                .sessionTimeoutMs(5 * SECOND).connectionTimeoutMs(3 * SECOND).retryPolicy(rp).build();
        cfFluent.start();
        System.out.println("Server connected...");
        return cfFluent;
    }
}
