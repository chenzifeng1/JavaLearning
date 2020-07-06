package RPC.RMI;


import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;

/**
 * @program: RPC
 * @author: chenzifeng
 * @description: RMI启动类代码
 * @create: 2020-06-29 18:54
 **/

public class RMIServerMain {

    //注册服务
    public static void main(String[] args) {
        try {
            //指定端口，防止防火墙拦截
            LocateRegistry.createRegistry(8866);
            //创建服务
            RMISocketFactory.setSocketFactory(new CustomerSocketFactory());

            RPCService rpcService = new RPCServiceImpl();
            Naming.bind("rmi://localhost:8866/RPCService",rpcService);

            System.out.println("服务已启动");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
