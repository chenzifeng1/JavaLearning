package RPC.Netty.Client;

import RPC.Netty.protocol.RPCRequest;
import RPC.Netty.protocol.RPCResponse;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: RPC.Netty.Client
 * @author: chenzifeng
 * @description: 对出站和入站的数据进行处理
 * @create: 2020-07-06 19:19
 **/

public class ClientHandler extends ChannelDuplexHandler {

    /**
     * Map维护请求对象ID和响应结果的映射关系
     */
    private final Map<String ,DefaultFuture> futureMap = new ConcurrentHashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /* 猜测：此处应该是建立响应结果与请求对象Id的映射关系，传过来的两个参数：
        * ChannelHandlerContext是一个接口
        * msg是传过来的响应数据
        * 验证猜测：猜测错误，此处是将传过来的msg封装为DefaultFuture的处理过程
        */
        if (msg instanceof RPCResponse){
            RPCResponse rpcResponse = (RPCResponse) msg;
            //Map已经维护好了对应的映射关系
            DefaultFuture defaultFuture = futureMap.get(rpcResponse.getResponseId());
            // 异步响应？
            defaultFuture.setRpcResponse(rpcResponse);
        }

        super.channelRead(ctx,msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof RPCRequest){
            RPCRequest rpcRequest = (RPCRequest) msg;
            //在发送请求之前，首先在客户端维护一个请求的Id,并关联一个DefaultFuture的实例
            futureMap.put(rpcRequest.getRequestId(),new DefaultFuture());
        }
        super.write(ctx,msg, promise);
    }

    /**
     * 获取响应数据
     * @param responseId
     * @return
     */
    public RPCResponse getRPCResponse(String responseId){
        try {
            DefaultFuture defaultFuture = futureMap.get(responseId);
            return defaultFuture.getRpcResponse(10);
        }finally {
            //获取响应之后移除对应关系
            futureMap.remove(responseId);
        }
    }
}
