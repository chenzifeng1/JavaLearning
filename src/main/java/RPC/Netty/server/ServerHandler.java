package RPC.Netty.server;

import RPC.Netty.protocol.RPCRequest;
import RPC.Netty.protocol.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.InvocationTargetException;


/**
 * @program: RPC.Netty.server
 * @author: chenzifeng
 * @description:
 * @create: 2020-07-09 20:00
 **/
@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<RPCRequest> implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCRequest rpcRequest) throws Exception {
        RPCResponse rpcResponse = new RPCResponse();
        rpcRequest.setRequestId(rpcRequest.getRequestId());
        try {
            //handler是服务端通过反射根据RPCRequest对象中的信息调用响应的方法。
            Object handler = handler(rpcRequest);
            log.info("返回结果：{}",handler);
            rpcResponse.setResult(handler);
        }catch (Throwable throwable){
            rpcResponse.setErrorInfo(throwable.getMessage());
            throwable.printStackTrace();
        }
        channelHandlerContext.writeAndFlush(rpcResponse);
    }



    /**
     * 服务端使用代理处理请求数据
     * 因为不知道请求数据的类型和属性
     * RPCRequest里面封装了关于请求数据Class类型和属性及方法的信息
     * @param request
     * @return
     */
    private Object handler(RPCRequest request) throws ClassNotFoundException,InvocationTargetException{
        //利用反射获取请求数据
        Class<?> clazz = Class.forName(request.getClassName());
        Object serverBean = applicationContext.getBean(clazz);
        log.info("ServerBean: {}",serverBean);
        Class<?> serverClass = serverBean.getClass();
        log.info("ServerClass：{}",serverClass);
        String methodName = request.getMethod();

        Class<?>[] parametersTypes = request.getParameterType();
        Object[] parameters = request.getParameters();

        //使用CGLIB Reflect
        FastClass fastClass = FastClass.create(serverClass);
        FastMethod fastMethod = fastClass.getMethod(methodName,parametersTypes);
        log.info("开始调用CGLIB动态代理执行服务端方法...");
        //调用方法
        return fastMethod.invoke(serverBean, parameters);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
