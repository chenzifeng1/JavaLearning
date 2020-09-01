package RPC.Netty.protocol;

import lombok.Data;
import lombok.ToString;

/**
 * @program: RPC.Netty.protocol
 * @author: chenzifeng
 * @description: Netty响应数据
 * @create: 2020-07-03 10:36
 *
 * 注：@Data生效需要打插件 https://blog.csdn.net/qq_34626671/article/details/102830793
 **/
@Data
@ToString
public class RPCResponse {
    /**
     * 响应id
     */
    private String requestId;
    /**
     * 错误信息
     */
    private String errorInfo;
    /**
     * 响应结果
     */
    private Object result;
}
