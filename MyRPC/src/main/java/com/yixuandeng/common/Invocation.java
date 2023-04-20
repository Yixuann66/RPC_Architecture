package com.yixuandeng.common;/**
 * @Author 85067
 * @create 18/04/2023 15:10
 */

import java.io.Serializable;

/**
 * @author 85067
 * @version 1.0
 * @description: 请求的公共模块,请求的对象 包括接口名称，方法名称，参数类型和参数 要实现序列化进行网络传递
 * @date 18/04/2023 15:10
 */


public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class[] parametersType;
    private Object[] parameters;


    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Invocation(String interfaceName, String methodName, Class[] parametersType, Object[] parameters) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parametersType = parametersType;
        this.parameters = parameters;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParametersType() {
        return parametersType;
    }

    public void setParametersType(Class[] parametersType) {
        this.parametersType = parametersType;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
