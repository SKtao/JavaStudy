package com.ktao.spring.beanPostProcessor;

import com.ktao.spring.beans.HelloService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/5/24
 **/
//@Component
public class HelloServicePostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean instanceof HelloService ? new HelloService() : bean;
    }
}
