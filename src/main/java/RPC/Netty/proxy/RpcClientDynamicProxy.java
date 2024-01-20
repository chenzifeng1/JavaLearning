package RPC.Netty.proxy;

import RPC.Netty.client.NettyClient;
import RPC.Netty.protocol.RPCRequest;
import RPC.Netty.protocol.RPCResponse;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * 客户端使用动态代理，在代理类中实现通信细节，动态代理类需要实现InvocationHandler接口
 * @param <T>
 */

@Slf4j
public class RpcClientDynamicProxy<T> implements InvocationHandler {
    /**
     * 服务端地址
     */
    private static final String SERVER_HOST = "127.0.0.1";
    /**
     * 服务端端口
     */
    private static final int SERVER_PORT = 8888;

    private Class<T> clzz;

    public RpcClientDynamicProxy(Class<T> clzz) throws Exception{
        this.clzz = clzz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest rpcRequest = new RPCRequest();
        String requestId = UUID.randomUUID().toString();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        Class<?>[] parameterType =  method.getParameterTypes();

        rpcRequest.setRequestId(requestId);
        rpcRequest.setClassName(className);
        rpcRequest.setMethod(methodName);
        rpcRequest.setParameterType(parameterType);
        rpcRequest.setParameters(args);

        log.info("请求内容 {}",rpcRequest);

        //开启Netty服务端，直连
        NettyClient client = new NettyClient(SERVER_HOST,SERVER_PORT);
        log.info("开始连接服务器 {}", new Date());
        client.connect();
        RPCResponse rpcResponse = client.send(rpcRequest);
        log.info("请求返回结果 {}",rpcResponse);
        return rpcResponse.getResult();
    }
}
