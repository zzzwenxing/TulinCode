package com.tuling.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Perms.*;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tommy
 * Created by Tommy on 2019/9/26
 **/
public class AclTest {
    ZooKeeper zooKeeper;


    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper("192.168.0.149", 2181, event -> {
            System.out.println(event);
        });
    }

    @Test
    public void getAclTest1() throws KeeperException, InterruptedException {
        List<ACL> acl = zooKeeper.getACL("/tuling", null);
        System.out.println(acl);
    }

    @Test
    public void setAclTest() throws KeeperException, InterruptedException {
        List<ACL> aclList = new ArrayList<>();
        int perm = ZooDefs.Perms.ADMIN | ZooDefs.Perms.READ|ZooDefs.Perms.WRITE;
        aclList.add(new ACL(perm, new Id("world", "anyone")));
        aclList.add(new ACL(ZooDefs.Perms.ALL, new Id("ip", "192.168.0.132")));
        zooKeeper.setACL("/tuling", aclList, 5);
    }

}
