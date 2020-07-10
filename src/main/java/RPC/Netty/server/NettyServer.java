package RPC.Netty.server;

import io.netty.channel.EventLoopGroup;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.spi.ServiceRegistry;

public class NettyServer implements InitializingBean {
    /**
     * boos-worker 应该使用了某种设计模式
     */
    private EventLoopGroup boss = null;
    private EventLoopGroup worker =null;
    /**
     * 服务处理器
     */
    @Autowired
    private ServerHandler serverHandler;

    @Override
    public void afterPropertiesSet() throws Exception {
        //可以使用Eureka或者Zookeeper做注册中心，暂不处理
//        ServiceRegistry registry  =new ZKServiceRegistry();

    }
}
