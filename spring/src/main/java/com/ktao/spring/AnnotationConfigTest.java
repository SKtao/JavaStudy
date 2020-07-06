package com.ktao.spring;

import com.ktao.spring.beans.HelloService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/5/20
 **/
public class AnnotationConfigTest {

    public static void main(String[] args) {
        // 注解配置容器加载（springboot启动时，会创建该context）
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(HelloService.class);
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        helloService.sayHello();
    }
}
