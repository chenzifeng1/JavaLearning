package RPC;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * @program: RPC
 * @author: chenzifeng
 * @description:
 * @create: 2020-06-24 08:52
 **/

public class RPCServiceImpl extends UnicastRemoteObject implements RPCService {
    String ipAddress = "chenzifeng1.github.io";

    protected RPCServiceImpl() throws RemoteException {
    }

    public String getMessage(String no) throws RemoteException {
        return ipAddress+":"+no;
    }
}