package RPC.Netty.Client;

import RPC.Netty.protocol.RPCResponse;

/**
 * @program: RPC.Netty.Client
 * @author: chenzifeng
 * @description:
 * @create: 2020-07-06 19:45
 **/

public class DefaultFuture {
    private RPCResponse rpcResponse;
    private volatile Boolean isSuccess = false;
    private final Object object = new Object();

    /**
     * 通过wait()和notify
     * @param timeOut
     * @return
     */
    public RPCResponse getRpcResponse(int timeOut) {
        synchronized (object){
            while(isSuccess){
                try {
                    object.wait(timeOut);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return rpcResponse;
    }

    public void setRpcResponse(RPCResponse rpcResponse) {
        if (isSuccess){
            return;
        }
        synchronized (object){
            this.rpcResponse = rpcResponse;
            this.isSuccess = true;
            object.notify();
        }

    }
}
