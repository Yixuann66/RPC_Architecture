package com.yixuandeng;


import com.yixuandeng.protocol.HttpServer;

/**
 * @author 85067
 * @version 1.0
 * @description: 服务提供者启动的时候需要准备执行的东西
 * @date 18/04/2023 12:15
 */
public class Provider {
    public static void main(String[] args) {
        // 支持接受一些网络请求，使用netty或者tomcat
        // 这个rpc框架--用户用哪个网络请求框架都可以
        // 导入了MyRPC这个包，来构造我们的httpserver
        HttpServer httpServer = new HttpServer();

        // 启动Tomcat
        httpServer.start("localhost",8080);

    }
}
