package com.yixuandeng.protocol;/**
 * @Author 85067
 * @create 18/04/2023 14:19
 */

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 85067
 * @version 1.0
 * @description: 自定义dispatcher容器，用于context接收到请求后进行的处理
 * @date 18/04/2023 14:19
 */
public class DispatcherServlet extends HttpServlet {

    /**
     * 如何请求，重写service方法即可
     * httpServlet：对get。post等请求方法进行了判断和封装 if (method.equals("GET")) {}
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // rpc对于每一个不同类型的请求，都会使用不同的handler进行处理，不断创建handler的类
        new HttpServerHandler().handler(req, resp);



        // http封装的请求
        super.service(req, resp);
    }
}
