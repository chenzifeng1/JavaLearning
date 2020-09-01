package RPC.Netty.protocol;

import java.io.IOException;

/**
 * @program: RPC.Netty.protocol
 * @author: chenzifeng
 * @description: 自定义序列化接口
 * @create: 2020-07-03 10:43
 **/

public interface Serializer {

    /**
     * 将对象序列化成字节数组
     * @param object
     * @return
     * @throws IOException
     */
    byte[] serialize(Object object) throws IOException;

    /**
     * 将字节数组反序列化成对象
     * @param bytes
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T deserialize(Class<T> clazz,byte[] bytes) throws IOException;

}
