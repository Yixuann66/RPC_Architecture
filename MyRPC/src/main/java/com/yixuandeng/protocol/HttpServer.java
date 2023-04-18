package com.yixuandeng.protocol;/**
 * @Author 85067
 * @create 18/04/2023 13:57
 */

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * @author 85067
 * @version 1.0
 * @description: RPC的server，相当于tomcat之类的
 * @date 18/04/2023 13:57
 */
public class HttpServer {

    /**
     * 调用server的请求方法
     * @param hostname 域名
     * @param port 端口
     */
    public void start(String hostname, Integer port ){
        // 1. 读取用户的配置 server.name = Tomcat/netty 从配置文件或者nacos中读取都可以
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        // 两个核心组件连接器（Connector）和容器（Container）
        // Connector
        Connector connector = new Connector();
        connector.setPort(port);

        // Engine：可以处理Tomcat所接收到所有请求，不管这些请求是请求哪个应用或哪个Servlet的。
        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        //Host：可以处理某个特定域名的所有请求 。一个Host表示一个虚拟服务器，可以给每个Host配置一个域名
        Host host = new StandardHost();
        host.setName(hostname);

        //Context：可以处理某个应用的所有请求。 一个Context就是一个应用，一个项目
        String contextPath = "";
        Context standartContext = new StandardContext();
        standartContext.setPath(contextPath);
        standartContext.addLifecycleListener(new Tomcat.FixContextListener());



        //并且这四个Servlet容器是具有层次关系的：一个Engine下可以有多个Host，一个Host下可以有多个Context，
        // 一个Context下可以有多个Wrapper，一个Wrapper下可以有多个Servlet实例对象。
        host.addChild(standartContext);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        // 如何处理请求
        //Wrapper：可以处理某个Servlet的所有请求。 一个Wrapper表示一个Servlet的包装，Wrapper在后文详解
        // 添加DispatcherServlet servlet容器
        tomcat.addServlet(contextPath,"dispatcher",new DispatcherServlet());

        // context接收到的所有请求（“/*”），都会交给名字为dispatcher的servlet容器来处理
        standartContext.addServletMappingDecoded("/*","dispatcher");



        // 开启tomcat
        try {
            tomcat.start();
            tomcat.getServer().await();


        }catch (LifecycleException e){
            e.printStackTrace();
        }



    }

}
