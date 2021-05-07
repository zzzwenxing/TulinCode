package com.tuling.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Tommy
 * Created by Tommy on 2019/9/26
 **/
public class ChildrenTest {
    ZooKeeper zooKeeper;


    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper("192.168.0.149", 2181, event -> {
            System.out.println(event);
        });
    }

    @Test
    public void getChild() throws KeeperException, InterruptedException {
        zooKeeper.getChildren("/tuling", null).stream().forEach(System.out::println);
        Stat stat = new Stat();
        zooKeeper.getChildren("/tuling", null, stat);
        System.out.println(stat);
    }
}
