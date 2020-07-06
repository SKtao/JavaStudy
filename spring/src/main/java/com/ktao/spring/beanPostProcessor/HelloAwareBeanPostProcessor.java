package com.ktao.spring.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/5/24
 **/
//@Component
public class HelloAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        System.out.println("test Aware");
        return bean;
    }
}
