package com.ktao.spring.scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

public class ClassPathBeanDefinitionScannerTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
        int beanCount = scanner.scan("com.ktao.study");
        System.out.println("Bean Count: " + beanCount);
    }
}
