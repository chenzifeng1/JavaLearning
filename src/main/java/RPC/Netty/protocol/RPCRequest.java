package RPC.Netty.protocol;


/**
 * @program: RPC.Netty.protocol
 * @author: chenzifeng
 * @description: Netty请求数据
 * @create: 2020-07-03 08:51
 **/

public class RPCRequest {
    /**
     * 请求对象Id
     */
    private String clssId;
    /**
     * 请求对象名
     */
    private String className;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 参数类型
     */
    private Class<?> parameterType;
    /**
     * 参数
     */
    private Object[] parameters;

    @Override
    public String toString() {
        return "RPCRequest{" +
                "clssId='" + clssId + '\'' +
                ", className='" + className + '\'' +
                ", method='" + method + '\'' +
                '}';
    }

    public String getClssId() {
        return clssId;
    }

    public void setClssId(String clssId) {
        this.clssId = clssId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class<?> getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class<?> parameterType) {
        this.parameterType = parameterType;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
