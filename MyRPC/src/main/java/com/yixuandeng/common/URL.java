package com.yixuandeng.common;/**
 * @Author 85067
 * @create 19/04/2023 15:19
 */

import java.io.Serializable;

/**
 * @author 85067
 * @version 1.0
 * @description: TODO
 * @date 19/04/2023 15:19
 */
public class URL implements Serializable {

    private String hostname;

    private Integer port;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public URL(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }
}
