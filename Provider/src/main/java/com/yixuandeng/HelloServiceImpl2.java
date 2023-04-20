package com.yixuandeng;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 18/04/2023 12:00
 */
public class HelloServiceImpl2 implements HelloService{

    @Override
    public String sayHello(String name) {


        return "hello"+name;
    }
}
