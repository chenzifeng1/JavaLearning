package RPC.Netty.server;

import io.netty.channel.EventLoopGroup;
import org.springframework.beans.factory.InitializingBean;

public class NettyServer implements InitializingBean {
    private EventLoopGroup boss = null;
    private EventLoopGroup worker =null;




    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
