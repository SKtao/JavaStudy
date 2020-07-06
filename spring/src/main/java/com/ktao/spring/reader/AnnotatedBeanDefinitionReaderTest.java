package com.ktao.spring.reader;


import com.ktao.spring.beans.ServiceC;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotatedBeanDefinitionReaderTest {
    /**
     * 读取bean定义Reader
     * AnnotatedBeanDefinitionReader
     * 1.注册内置BeanPostProcessor
     * 2.注册相关的BeanDefinition
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 手动加载
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(context);
        // 注册类BeanDifinition
        reader.register(ServiceC.class);
        System.out.println("just test");
    }
}
