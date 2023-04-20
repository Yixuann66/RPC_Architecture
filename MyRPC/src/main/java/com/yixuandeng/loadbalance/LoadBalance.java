package com.yixuandeng.loadbalance;/**
 * @Author 85067
 * @create 19/04/2023 16:25
 */

import com.yixuandeng.common.URL;

import java.util.List;
import java.util.Random;

/**
 * @author 85067
 * @version 1.0
 * @description: 负载均衡的策略
 * @date 19/04/2023 16:25
 */
public class LoadBalance {


    /**
     * 模拟随机负载均衡算法
     * @param urls
     * @return
     */
    public static URL random(List<URL> urls){
        Random random = new Random();
        // 根据随机数字，找到哪一个url
        int nextInt = random.nextInt(urls.size());



        return urls.get(nextInt);
    }

}
