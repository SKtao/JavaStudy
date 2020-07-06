package com.ktao.spring.beans;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/5/19
 **/
public class ServiceC {
    private ServiceB serviceB;

    public ServiceC(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
