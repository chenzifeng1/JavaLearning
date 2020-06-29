package RPC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * @program: RPC
 * @author: chenzifeng
 * @description:
 * @create: 2020-06-28 14:13
 **/

public class CustomerSocketFactory extends RMISocketFactory{
    private static Logger logger = LoggerFactory.getLogger(CustomerSocketFactory.class);

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host,port);
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if (port ==0){
            port = 8855;
        }
        return null;
    }
}
