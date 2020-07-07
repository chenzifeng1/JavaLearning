package RPC.Netty.client;

import RPC.Netty.protocol.*;
import com.alibaba.fastjson.serializer.JSONSerializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @program: RPC.Netty
 * @author: chenzifeng
 * @description:
 * @create: 2020-07-06 18:51
 **/

public class NettyClient {
    //
    private EventLoopGroup eventLoopGroup;
    //
    private Channel channel;
    //客户端处理器
    private ClientHandler clientHandler;
    //ip地址
    private String host;
    //端口
    private Integer port;
    //最大重试次数
    private static final int MAX_RETRY = 5;

    public NettyClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void connect(){
        clientHandler = new ClientHandler();
        eventLoopGroup = new NioEventLoopGroup();
        //启动类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class) //指定传输用的Channel
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,500)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        //添加编码器
                        channelPipeline.addLast(new RpcEncoder(RPCRequest.class, new SerializerImpl()));
                        //添加解码器
                        channelPipeline.addLast(new RpcDecoder(RPCResponse.class,new SerializerImpl()));
                        //请求处理类
                        channelPipeline.addLast(clientHandler);
                    }
                });
        connect(bootstrap,host,port,MAX_RETRY);
    }

    /**
     * 失败重连机制
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     */
    private void connect(Bootstrap bootstrap, String host, int port, int retry){

    }
}
