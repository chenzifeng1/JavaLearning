package RPC;

import java.rmi.RemoteException;

/**
 * @program: RPC
 * @author: chenzifeng
 * @description:
 * @create: 2020-06-24 08:52
 **/

public class RPCServiceImpl implements RPCService {

    private String name;

    @Override
    public String getMessage(String no) throws RemoteException {
        return System.currentTimeMillis()+":"+no;

    }
}
