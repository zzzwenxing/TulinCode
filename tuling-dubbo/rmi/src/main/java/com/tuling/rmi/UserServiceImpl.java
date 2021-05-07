package com.tuling.rmi;

import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Tommy
 * Created by Tommy on 2019/12/1
 **/
public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    protected UserServiceImpl() throws RemoteException {
    }


    @Override
    public String getName(Integer id) {
        return String.format("myName is luban :%s", ManagementFactory.getRuntimeMXBean().getName());
    }
}
