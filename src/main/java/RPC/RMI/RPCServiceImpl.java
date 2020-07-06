package RPC.RMI;

import java.rmi.RemoteException;
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

    @Override
    public String getMessage(String no) throws RemoteException {
        return ipAddress+":"+no;
    }
}
