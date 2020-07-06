package RPC.Netty.Client;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;

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

    private ClientHandler clientHandler;
}
