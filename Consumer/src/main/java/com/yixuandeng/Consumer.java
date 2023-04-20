package com.yixuandeng;/**
 * @Author 85067
 * @create 18/04/2023 12:02
 */

import com.yixuandeng.common.Invocation;
import com.yixuandeng.protocol.HttpClient;
import com.yixuandeng.proxy.ProxyFactory;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 18/04/2023 12:02
 */
public class Consumer {
    public static void main(String[] args) {

        // 1. 调用另外一个应用providor的服务，调用他的HelloServiceImpl的方法
        // 1.1 远程的方法/过程调用：在本地调用另外一个应用的方法
        // 通过获取接口的代理对象(在rpc框架里面),针对一些接口产生代理对象
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        // 2. 执行方法,在后面进行动态代理,取出方法名和参数,包装成invocation进行远程调用,发给Provider服务提供端
        String sayHello = helloService.sayHello("dengyixuan");





        // 发送invocation对象的请求 构造Invocation请求 调用的接口，方法的名字和方法的参数类型和参数值
//        Invocation invocation = new Invocation(HelloService.class.getName(),"sayHello"
//                ,new Class[]{String.class}
//                ,new Object[]{"Deng yi Xuan"});
//
//        // 网络进行远程调用 rpc提供了一个HTTP server作为客户端的调用
//        HttpClient httpClient = new HttpClient();
//        String res = httpClient.send("localhost", 8080, invocation);
//        System.out.println("Consumer的res:"+res);


    }
}
