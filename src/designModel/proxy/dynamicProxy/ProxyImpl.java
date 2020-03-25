package designModel.proxy.dynamicProxy;

import designModel.proxy.dynamicProxy.Proxy;

public class ProxyImpl implements Proxy {
    @Override
    public void proxyMethod() {
        System.out.println("this is ProxyImpl");
    }
}
