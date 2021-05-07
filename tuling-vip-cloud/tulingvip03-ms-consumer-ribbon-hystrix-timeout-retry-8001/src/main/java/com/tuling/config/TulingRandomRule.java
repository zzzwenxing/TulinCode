package com.tuling.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 自定义的随机策略
 * Created by smlz on 2019/4/17.
 */
public class TulingRandomRule extends AbstractLoadBalancerRule {

    Random rand;

    public TulingRandomRule() {
        rand = new Random();
    }

    private int currentIndex = 0;

    private List<Server> currentChooseList = new ArrayList<>();

    /**
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }

            //第一次进来，随机一个下标
            int index = rand.nextInt(serverCount);

            //当前的轮询次数小于等于5
            if(currentIndex<5) {
                if(currentChooseList.isEmpty()) {
                    currentChooseList.add(upList.get(index));
                }
                //当前的次数加1
                currentIndex++;
                return currentChooseList.get(0);

            }else {
                currentChooseList.clear();
                currentChooseList.add(0,upList.get(index));
                currentIndex=0;
            }

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub

    }

}
