package com.ktao.spring.beans;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/5/21
 **/
@Service
public class ServiceD  {
    @Resource
    private HelloService helloService;

}
