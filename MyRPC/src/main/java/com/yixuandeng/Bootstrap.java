package com.yixuandeng;/**
 * @Author 85067
 * @create 19/04/2023 17:08
 */

import com.yixuandeng.common.URL;
import com.yixuandeng.protocol.HttpServer;
import com.yixuandeng.register.LocalRegister;
import com.yixuandeng.register.MapRemoteRegister;

/**
 * @author 85067
 * @version 1.0
 * @description: RPC启动方法 也可以定义Register方法
 * @date 19/04/2023 17:08
 */
public class Bootstrap {

//    public static void start(){
//        // 根据接口和实现类注册服务和方法到map中，方便以后进行查找
//        // 开启tomcat的时候再处理请求
//        LocalRegister.regist(HelloService.class.getName(),"1.0",HelloServiceImpl.class);
//        //LocalRegister.regist(HelloService.class.getName(),"2.0",HelloServiceImpl2.class);
//
//        // 步揍2，本地注册接口和实现类后
//        //       进行服务的注册和发现，注册中心注册
//        //       常见的注册中心：Redis,Nacos，Zookeeper,eraka,map数据结构
//        // 配置这个机器的地址，保存在MapRemoteRegister（注册中心）中
//        URL url = new URL("localhost",8080);
//        MapRemoteRegister.regist(HelloService.class.getName(),url);
//
//
//
//        // 支持接受一些网络请求，使用netty或者tomcat
//        // 这个rpc框架--用户用哪个网络请求框架都可以
//        // 导入了MyRPC这个包，来构造我们的httpserver
//        HttpServer httpServer = new HttpServer();
//
//        // 启动Tomcat
//        httpServer.start("localhost",8080);
//    }
}
