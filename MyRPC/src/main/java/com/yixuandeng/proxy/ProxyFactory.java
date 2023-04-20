package com.yixuandeng.proxy;/**
 * @Author 85067
 * @create 18/04/2023 18:13
 */

import com.yixuandeng.common.Invocation;
import com.yixuandeng.common.URL;
import com.yixuandeng.loadbalance.LoadBalance;
import com.yixuandeng.protocol.HttpClient;
import com.yixuandeng.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author 85067
 * @version 1.0
 * @description: 通过获取接口的代理对象(在rpc框架里面), 针对一些接口产生代理对象
 * @date 18/04/2023 18:13
 */
public class ProxyFactory {

    /**
     * 获取对应接口的代理对象
     *
     * @param interfaceClass
     * @param <T>
     * @return
     */
    public static <T> T getProxy(Class interfaceClass) {
        // 读取用户的配置
        // 使用jdk的动态代理,代理哪一个接口
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //发送invocation对象的请求 构造Invocation请求 调用的接口，方法的名字和方法的参数类型和参数值
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName()
                        , method.getParameterTypes(),
                        args);

                // 网络进行远程调用 rpc提供了一个HTTP server作为客户端的调用
                HttpClient httpClient = new HttpClient();
                // 根据调用的接口，获取到要调用的方法，找到对应的url
                // 1 服务发现
                List<URL> list = MapRemoteRegister.get(interfaceClass.getName());


                // 3 服务调用
                // 如何找到provider的一个ip地址和端口号：使用注册中心，服务调用者从注册中心将ip地址取出来Map<String,List<URL>>;名字和url+端口
                String res = null;


                // 调用的最大次数
                int max = 3;

                while (max > 0) {
                    // 2 负载均衡 不用同一个url（使用list记录已经调用过的url）
                    URL random = LoadBalance.random(list);
                    try {
                        // 3.1 模拟服务出现异常，容错机制
                        res = httpClient.send(random.getHostname(), random.getPort(), invocation);
                        System.out.println("Consumer的res:" + res);
                        max = 0;

                    } catch (Exception e) {
                        // error-callback 报错回调 服务容错
                        // 服务重试
                        if (max-- != 0) {
                            continue;
                        }
                        return "服务报错了";
                    }

                }
//                method.invoke(proxy, args);
                return res;
            }

        });

        return (T) proxyInstance;
    }

}
