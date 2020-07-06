package com.ktao.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/5/19
 **/
public class ServiceB {

    private ServiceA serviceA;

//    public ServiceB(ServiceA serviceA) {
//        this.serviceA = serviceA;
//    }

    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public void testB(){
        serviceA.testA();
        System.out.println("test B");
    }
}
