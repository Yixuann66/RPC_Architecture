package com.yixuandeng.register;/**
 * @Author 85067
 * @create 18/04/2023 15:32
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @author 85067
 * @version 1.0
 * @description: 本地注册interface 和 实现类
 * @date 18/04/2023 15:32
 */
public class LocalRegister {

    // key 是接口的名字，class是接口的实现类
    private static Map<String,Class> map;

    static {
        map = new HashMap<>();
    }

    /**
     * 注册方法 服务提供者开启tomcat的时候（httpserver.start()），先注册
     * @param interfaceName 要调用的接口的名字 根据接口名字找到对应的实现类
     * @param implementClass 要调用的实现类的名字
     * @Param version 指定版本号
     */
    public static void regist(String interfaceName,String version, Class implementClass){
        map.put(interfaceName + version, implementClass);
    }

    /**
     * 远程调用根据接口名字获取对应的实现类
     * @param interfaceName
     * @return
     */
    public static Class get(String interfaceName,String version ) {
        return map.get(interfaceName+version);
    }


}
