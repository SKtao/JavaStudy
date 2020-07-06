package com.ktao.spring.aop;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/5/21
 **/
@Aspect
@Component
@Log4j2
public class ServiceAspect {

    @Pointcut(value = "execution(* com.ktao.spring.beans.*.*(..))")
    public void servicePoint() {
    }


    @Before("servicePoint()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("before ....");
    }


    @After("servicePoint()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("after ....");
    }
}
