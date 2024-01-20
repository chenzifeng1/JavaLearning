package RPC.Netty.client;

import RPC.Netty.protocol.*;
import com.alibaba.fastjson.serializer.JSONSerializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: RPC.Netty
 * @author: chenzifeng
 * @description:
 * @create: 2020-07-06 18:51
 **/
@Slf4j
public class NettyClient {
    /**
     * 事件处理器集合：每个I/O操作都交给EventLoop来处理，
     * 而EventLoopGroup里面通常包括大于一个的EventLoop
     */
    private EventLoopGroup eventLoopGroup;
    /**
     * 定义了与Socket进行交互的操作集
     */
    private Channel channel;
    /**
     * 客户端处理器
     */
    private ClientHandler clientHandler;
    /**
     * ip地址
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 最大重试次数
     */
    private static final int MAX_RETRY = 5;

    public NettyClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        clientHandler = new ClientHandler();
        eventLoopGroup = new NioEventLoopGroup();
        //启动类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                //指定传输用的Channel
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 500)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        //添加编码器
                        channelPipeline.addLast(new RpcEncoder(RPCRequest.class, new SerializerImpl()));
                        //添加解码器
                        channelPipeline.addLast(new RpcDecoder(RPCResponse.class, new SerializerImpl()));
                        //请求处理类
                        channelPipeline.addLast(clientHandler);
                    }
                });
        connect(bootstrap, host, port, MAX_RETRY);
    }

    /**
     * 失败重连机制
     *
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     */
    private void connect(Bootstrap bootstrap, String host, int port, int retry) {
        // ChannelFuture来获取响应结果
        ChannelFuture channelFuture = bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("连接成功");
            } else if (retry == 0) {
                log.error("重连失败");
            } else {
                //重连次数
                int order = (MAX_RETRY - retry) + 1;
                //本次重连间隔
                int delay = 1 << order;
                log.error("第 {} 次重连失败，时间：{}", order, new Date());
                //定时任务
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
        channel = channelFuture.channel();
    }


    /**
     * 发送数据：使用channel将请求发送之后进入等待状态，如果响应来了会被唤醒，唤醒时说明ClientHandler响应对象中已经有请求对应的响应了，使用请求Id获取该响应即可。
     *
     * @param rpcRequest
     * @return
     */
    public RPCResponse send(final RPCRequest rpcRequest) {
        try {
            channel.writeAndFlush(rpcRequest).await();
        } catch (InterruptedException e) {
            log.error("发送数据失败", e);
        }
        return clientHandler.getRPCResponse(rpcRequest.getRequestId());
    }


    public void close() {
        //关闭通道，同步不可中断
        eventLoopGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
    }
}
