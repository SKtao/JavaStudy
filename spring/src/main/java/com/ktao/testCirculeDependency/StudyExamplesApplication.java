package com.ktao.testCirculeDependency;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan
@Configuration
public class StudyExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyExamplesApplication.class, args);
        // 注解配置容器加载（springboot启动时，会创建该context）
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(StudyExamplesApplication.class);
//        Service1 service1 = (Service1) applicationContext.getBean("service1");
    }
}
