package com.ktao.spring;
import com.ktao.spring.beans.ServiceB;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/5/19
 **/
public class ClassXmlContextTest {

    public static void main(String[] args) {
        // 自动加载配置文件刷新容器
        autoLoadContainer();

        // 抽离容器启动核心逻辑
//        manualLoadContainer();
    }

    private static void manualLoadContainer() {
        // 1. 工厂实例化（核心工作：创建beanFactory + 读取配置文件构建BeanDefinition）
        // 创建一个beanFactory(默认：DefaultListableBeanFactory)
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 定义一个资源读取Reader
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 加载资源配置(xml中bean的定义)
        beanDefinitionReader.loadBeanDefinitions("spring/spring-beans.xml");

        // 2. Bean实例化
        // getBean：实例化并获取bean
        ServiceB serviceB = (ServiceB) beanFactory.getBean("serviceB");
        serviceB.testB();
    }

    /**
     * 自动加载配置文件刷新容器
     */
    private static void autoLoadContainer() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-beans.xml");
        ServiceB serviceB = (ServiceB) context.getBean("serviceB");
        serviceB.testB();
    }
}


