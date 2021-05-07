package com.tuling.dubbo;

import org.apache.dubbo.config.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tommy
 * Created by Tommy on 2019/12/4
 **/
public class DubboServer {

    public static void main(String[] args) throws IOException {
        // 开始 暴露 UserService 服务
        // application
        // protocol -dubbo 协议
        // register
        // service
        ApplicationConfig applicationConfig
                = new ApplicationConfig("sample-app");
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setSerialization("fastjson");
        protocolConfig.setPort(-1);//20880
        RegistryConfig registryConfig
                = new RegistryConfig("zookeeper://192.168.0.147:2181");

        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setInterface("com.tuling.client.UserService");
        serviceConfig.setRef(new UserServiceImpl());
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);
//        serviceConfig.setToken(true);
//        setLoadbalance(serviceConfig);
        serviceConfig.export();

        System.out.println("服务已暴露");
        System.in.read();
    }

    public static void setLoadbalance(ServiceConfig serviceConfig) {
        serviceConfig.setLoadbalance("consistenthash");
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("findUser");
        Map<String, String> paramter = new HashMap<>();
        paramter.put("hash.arguments", "0,1");
        paramter.put("hash.nodes", "320");
        methodConfig.setParameters(paramter);
        serviceConfig.setMethods(Arrays.asList(methodConfig));
        //
    }

    public static void setMock(ServiceConfig serviceConfig, String server) {
        serviceConfig.setRef(new MockService(server));
    }

}
