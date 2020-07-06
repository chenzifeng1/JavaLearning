package RPC.Netty.Client;

import RPC.Netty.protocol.RPCResponse;

/**
 * @program: RPC.Netty.Client
 * @author: chenzifeng
 * @description: 该类是响应数据的包装类
 * 三个属性：
 * RPC响应 rpcResponse
 * 是否接到响应：isSuccess （猜测）
 * 请求对象：object （猜测）
 * @create: 2020-07-06 19:45
 **/

public class DefaultFuture {
    private RPCResponse rpcResponse;
    private volatile Boolean isSuccess = false;
    private final Object object = new Object();

    /**
     * 通过wait()和notify()方法来实现异步调用
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
