package com.yixuandeng;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 18/04/2023 12:00
 */
public class HelloServiceImpl implements HelloService{

    @Override
    public String sayHello(String name) {


        return "hello"+name;
    }
}
