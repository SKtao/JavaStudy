package com.ktao.testCirculeDependency.beans;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/5/22
 **/
@Service
public class Service1 implements ServiceAware{

    @Resource
    private Service2 service2;

    @Override
    public void aware() {

    }
}
