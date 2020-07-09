package RPC.Netty.protocol;


import lombok.Data;
import lombok.ToString;

/**
 * @program: RPC.Netty.protocol
 * @author: chenzifeng
 * @description: Netty请求数据
 * @create: 2020-07-03 08:51
 **/
@Data
@ToString
public class RPCRequest {
    /**
     * 请求对象Id
     */
    private String requestId;
    /**
     * 请求对象的类名
     */
    private String className;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 参数类型
     */
    private Class<?>[] parameterType;
    /**
     * 参数
     */
    private Object[] parameters;

}
