package test.tuling.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Tommy
 * Created by Tommy on 2019/9/23
 **/
public class MasterResolve {
    private String server = "192.168.0.149:2181";
    private ZkClient zkClient;
    private static final String rootPath = "/tuling-master";
    private static final String servicePath = rootPath + "/service";
    private String nodePath;
    private volatile boolean master = false;
    private static MasterResolve resolve;

    private MasterResolve() {
        zkClient = new ZkClient(server, 2000, 5000);
        buildRoot();
        createServerNode();
    }

    public static MasterResolve getInstance() {
        if (resolve == null) {
            resolve = new MasterResolve();
        }
        return resolve;
    }


    // 构建根节点
    public void buildRoot() {
        if (!zkClient.exists(rootPath)) {
            zkClient.createPersistent(rootPath);
        }
    }

    // 创建server节点
    public void createServerNode() {
        nodePath = zkClient.createEphemeralSequential(servicePath, "slave");
        System.out.println("创建service节点:" + nodePath);
        initMaster();
        initListener();
    }


    private void initMaster() {
        boolean existMaster = zkClient.getChildren(rootPath)
                .stream()
                .map(p -> rootPath + "/" + p)
                .map(p -> zkClient.readData(p))
                .anyMatch(d -> "master".equals(d));
        if (!existMaster) {
            zkClient.writeData(nodePath, "master");
            master = true;
            System.out.println("当前当选master");
        }
    }

    private void initListener() {
        zkClient.subscribeChildChanges(rootPath, (parentPath, currentChilds) -> {
            doElection();//  执行选举
        });
    }

    // 执行选举
    public void doElection() {
        Map<String, Object> childData = zkClient.getChildren(rootPath)
                .stream()
                .map(p -> rootPath + "/" + p)
                .collect(Collectors.toMap(p -> p, p -> zkClient.readData(p)));
        if (childData.containsValue("master")) {
            return;
        }

        childData.keySet().stream().sorted().findFirst().ifPresent(p -> {
            if (p.equals(nodePath)) { // 设置最小值序号为master 节点
                zkClient.writeData(nodePath, "master");
                master = true;
                System.out.println("当前当选master" + nodePath);
            }
        });

    }


    public static boolean isMaster() {
        return getInstance().master;
    }
}
