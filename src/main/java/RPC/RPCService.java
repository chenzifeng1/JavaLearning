package RPC;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RPCService extends Remote{

    public String getMessage(String no) throws RemoteException;
}
