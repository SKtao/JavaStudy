package com.ktao.spring.beans;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/5/19
 **/
@Service
// 控制bean加载顺序
//@DependsOn(value = "serviceD")
public class HelloService {

    @Resource
    private ServiceD serviceD;

    public void sayHello(){
        System.out.println("hello world");
    }
}
