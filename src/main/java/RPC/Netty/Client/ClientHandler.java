package RPC.Netty.Client;

import io.netty.channel.ChannelDuplexHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: RPC.Netty.Client
 * @author: chenzifeng
 * @description: 对出站和入站的数据进行处理
 * @create: 2020-07-06 19:19
 **/

public class ClientHandler extends ChannelDuplexHandler {

    private final Map<String ,DefaultFuture> futureMap = new ConcurrentHashMap<>();
}
