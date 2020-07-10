package RPC.Netty.server;

import RPC.Netty.protocol.*;
import com.alibaba.fastjson.serializer.JSONSerializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.spi.ServiceRegistry;
import java.util.ArrayList;

public class NettyServer implements InitializingBean {
    /**
     * 负责处理客户端连接的线程池
     */
    private EventLoopGroup boss = null;
    /**
     * 负责处理读写操作的线程池
     */
    private EventLoopGroup worker =null;
    /**
     * 服务处理器
     */
    @Autowired
    private ServerHandler serverHandler;

    @Override
    public void afterPropertiesSet() throws Exception {
        //可以使用Eureka或者Zookeeper做注册中心，暂不处理
//        ServiceRegistry registry  =new ZKServiceRegistry();\
        ArrayList<Class<?> > categories = new ArrayList<>();

        ServiceRegistry registry = new ServiceRegistry(categories.iterator());
        start(registry);
    }

    public void start(ServiceRegistry registry) throws Exception{
        //建立负责客户端连接的线程池
        boss = new NioEventLoopGroup();
        //建立负责读写操作的线程池
        worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.option(NioChannelOption.SO_BACKLOG, 1024);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline channelPipeline = socketChannel.pipeline();
                //设置编码器
                channelPipeline.addLast(new RpcEncoder(RPCRequest.class, new SerializerImpl()));
                //设置解码器
                channelPipeline.addLast(new RpcDecoder(RPCResponse.class,new SerializerImpl()));
                //设置处理器
                channelPipeline.addLast(serverHandler);
            }
        });
        bind(serverBootstrap,8888);
    }

    /**
     * 
     * @param serverBootstrap
     * @param port
     */
    public void bind(final ServerBootstrap serverBootstrap,int port){

    }
}
