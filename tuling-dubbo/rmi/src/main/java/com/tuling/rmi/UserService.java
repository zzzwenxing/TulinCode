package com.tuling.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Tommy
 * Created by Tommy on 2019/12/1
 **/
public interface UserService extends Remote {

    String getName(Integer id) throws RemoteException;
}
