package com.yixuandeng;/**
 * @Author 85067
 * @create 18/04/2023 12:02
 */

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
        HelloService helloService = new HelloService();
        // 2. 执行方法
        String sayHello = helloService.sayHello("dengyixuan");

    }
}
