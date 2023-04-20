package com.yixuandeng;


import com.yixuandeng.common.URL;
import com.yixuandeng.protocol.HttpServer;
import com.yixuandeng.register.LocalRegister;
import com.yixuandeng.register.MapRemoteRegister;

/**
 * @author 85067
 * @version 1.0
 * @description: 服务提供者启动的时候需要准备执行的东西 提前将接口和接口对应的实现类进行本地注册，放在LocalRegister中
 *               根据版本号的不同找到对应的实现类
 *               provider只需要提供本地注册和启动Tomcat，就可以被别人远程访问和调用
 *               启动TOMCAT-tomcat的start()方法-配置tomat参数-添加指定的servlet来处理特定路径下的请求-
 *               -构建自己处理请求的方法class DispatcherServlet extends HttpServlet
 *               -一个servlet 可以对应不同的httphandler
 *               -有请求来临时，触发handler()方法
 *               -根据请求传来的Invocation对象，根据本地注册的map找到要调用的方法，通过反射传入参数
 *               -执行远程的方法调用
 *               -调用完成之后，将结果返回（放在resp中）
 * @date 18/04/2023 12:15
 */
public class Provider {
    public static void main(String[] args) {

        // 根据接口和实现类注册服务和方法到map中，方便以后进行查找
        // 开启tomcat的时候再处理请求
        LocalRegister.regist(HelloService.class.getName(),"1.0",HelloServiceImpl.class);
        //LocalRegister.regist(HelloService.class.getName(),"2.0",HelloServiceImpl2.class);

        // 步揍2，本地注册接口和实现类后
        //       进行服务的注册和发现，注册中心注册
        //       常见的注册中心：Redis,Nacos，Zookeeper,eraka,map数据结构
        // 配置这个机器的地址，保存在MapRemoteRegister（注册中心）中
        URL url = new URL("localhost",8080);
        MapRemoteRegister.regist(HelloService.class.getName(),url);



        // 支持接受一些网络请求，使用netty或者tomcat
        // 这个rpc框架--用户用哪个网络请求框架都可以
        // 导入了MyRPC这个包，来构造我们的httpserver
        HttpServer httpServer = new HttpServer();

        // 启动Tomcat
        httpServer.start("localhost",8080);

    }
}
