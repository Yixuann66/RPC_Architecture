package com.yixuandeng.protocol;/**
 * @Author 85067
 * @create 18/04/2023 17:02
 */

import com.yixuandeng.common.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * @author 85067
 * @version 1.0
 * @description: 客户端
 * @date 18/04/2023 17:02
 */
public class HttpClient {

    /**
     * 发起远程调用
     * @param hostName 发送的主机地址
     * @param port  发送的端口号
     * @param invocation 发送的封装好的数据 可以指定要调用哪一个方法等等
     * @return
     */
    public String send(String hostName, Integer port, Invocation invocation) throws IOException {
        // 发送http请求，可以在这里读取用户的配置，发送指定的http请求
        try{
            URL url = new URL("http",hostName,port,"/");
            // 创建http连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            // 输出流
            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            System.out.println("point");
            oos.writeObject(invocation);
            oos.flush();
            oos.close();



            // 封装返回结果 输入流 阻塞式获取
            InputStream inputStream = httpURLConnection.getInputStream();
            String result = IOUtils.toString(inputStream);

            return result;
        } catch (MalformedURLException e) {

        } catch (IOException e) {
            throw e;
        }

        return "出错了";
    }

}
