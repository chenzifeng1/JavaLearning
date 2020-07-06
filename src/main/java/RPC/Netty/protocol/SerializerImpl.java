package RPC.Netty.protocol;

import com.alibaba.fastjson.JSON;


/**
 * @program: RPC.Netty.protocol
 * @author: chenzifeng
 * @description: 序列化接口的实现类,采用JSON传输
 * @create: 2020-07-03 10:47
 **/

public class SerializerImpl implements Serializer {
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz,byte[] bytes)  {
        return JSON.parseObject(bytes,clazz);
    }
}
