package RPC.Netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @program: RPC.Netty.protocol
 * @author: chenzifeng
 * @description:
 * @create: 2020-07-03 14:29
 **/

public class RpcDecoder extends ByteToMessageDecoder {
    private Class clazz;
    private Serializer serializer;

    public RpcDecoder(Class clazz, Serializer serializer) {
        this.clazz = clazz;
        this.serializer = serializer;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //因为编码时写入了一个int类型的数据，因此byteBuf里面的可读字节一定大于4，如果小于4则是无数据
        if (byteBuf.readableBytes()<4){
            return;
        }
        //标记当前读的位置
        byteBuf.markReaderIndex();
        int dataLength = byteBuf.readInt();
        //如果当前可读字节数小于数据长度，重置当前读的位置
        if (byteBuf.readableBytes()<dataLength){
            byteBuf.resetReaderIndex();
            return;
        }
        //将byteBuf中的数据读到data中
        byte[] data = new byte[dataLength];
        byteBuf.readBytes(data);
        list.add(data);
    }
}
