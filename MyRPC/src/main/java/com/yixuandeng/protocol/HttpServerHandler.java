package com.yixuandeng.protocol;/**
 * @Author 85067
 * @create 18/04/2023 14:43
 */

import com.yixuandeng.common.Invocation;
import com.yixuandeng.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 18/04/2023 14:43
 */
public class HttpServerHandler {


    /**
     * 专门处理请求的handler
     * @param req
     * @param resp
     */
    public void handler(HttpServletRequest req, HttpServletResponse resp){
        // 处理请求，consumer根据接口调用provider中的方法
        // 接口，方法，方法参数

        // 从请求里面读取Invocation对象
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            // 根据接口找到实现类对应的方法
            // 如何找到对应的实现类：使用本地注册的方法，避免遍历所有的类的开销（register包）
            String interfaceName = invocation.getInterfaceName();

            // 基于LocalRegister,根据接口和版本号找到对应的实现类
            Class classImpl = LocalRegister.get(interfaceName,"1.0");


            // 通过类来获取对应的方法：方法名加方法的参数列表 拿到实现类的sayhello方法
            // invocation是请求参数，包含方法名称，参数列表
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParametersType());


            // 通过反射的方式执行这个方法
            String result = (String) method.invoke(classImpl.newInstance(), invocation.getParameters());
            // 打印返回的结果
            System.out.println("服务调用完的结果是"+result);
            // 将结果写在resp中
            IOUtils.write(result,resp.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }

}
