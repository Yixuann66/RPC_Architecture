package com.yixuandeng.register;/**
 * @Author 85067
 * @create 19/04/2023 16:06
 */

import com.yixuandeng.common.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 85067
 * @version 1.0
 * @description: 自己实现的远程的注册中心，开启provider服务前进行注册
 * @date 19/04/2023 16:06
 */
public class MapRemoteRegister {

    /**
     * list的原因：可能这个provider是一个集群，有多个结点同时提供服务 private static
     */
    private static Map<String, List<URL>> map;

    static {
        map = new HashMap<>();
    }


    // 利用redis 或者 nacos等等进行服务注册，让多个不同进程（不同的微服务）都能发现
    // 本系统：写在文件上，map来存
    /**
     * 将Map<String, List<URL>> map 的服务注册数据保存在本地
     */
    private static void saveFile(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(map);

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    private static Map<String,List<URL>> getFile(){

        try {
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();

    }


    public static void regist(String interfaceName, URL url){
        List<URL> list = map.get(interfaceName);
        if (list == null ) {
            list = new ArrayList<>();

        }



        list.add(url);

        map.put(interfaceName,list);
        // 存到本地文件中
        saveFile();
    }

    public static List<URL> get(String interfaceName) {

        map = getFile();

        return map.get(interfaceName);
    }



//    public static Class get(String interfaceName,String version ) {
//        return map.get(interfaceName);
//    }



}
