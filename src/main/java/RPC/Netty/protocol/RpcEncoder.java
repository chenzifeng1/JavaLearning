package RPC.Netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @program: RPC.Netty.protocol
 * @author: chenzifeng
 * @description: 信息转字节编码
 * @create: 2020-07-03 14:12
 **/

public class RpcEncoder extends MessageToByteEncoder {
    private Class<?> clazz;
    private Serializer serializer;

    public RpcEncoder(Class<?> clazz, Serializer serializer) {
        this.clazz = clazz;
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        //首先判断一下初始化的编码器的信息类型是否一致
        if (clazz!=null && clazz.isInstance(o) ){
            //将信息编码
            byte[] bytes = serializer.serialize(o);
            //字节流中写入字节长度
            byteBuf.writeInt(bytes.length);
            //写入信息
            byteBuf.writeBytes(bytes);
        }
    }
}
