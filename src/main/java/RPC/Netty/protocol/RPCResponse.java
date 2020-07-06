package RPC.Netty.protocol;

/**
 * @program: RPC.Netty.protocol
 * @author: chenzifeng
 * @description: Netty响应数据
 * @create: 2020-07-03 10:36
 **/

public class RPCResponse {
    /**
     * 响应id
     */
    private String responseId;
    /**
     * 错误信息
     */
    private String errorInfo;
    /**
     * 响应结果
     */
    private Object result;
}
