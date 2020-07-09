package RPC.Netty.server;

import io.netty.channel.EventLoopGroup;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

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

    }
}
