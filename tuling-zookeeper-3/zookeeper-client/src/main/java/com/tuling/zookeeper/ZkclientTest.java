package com.tuling.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.junit.Before;

/**
 * @author Tommy
 * Created by Tommy on 2019/9/26
 **/
public class ZkclientTest {
    ZkClient zkClient;
    @Before
    public void init() {
         zkClient = new ZkClient("192.168.0.149:2181", 5000, 5000);
    }
    public void createTest(){
    }
}
