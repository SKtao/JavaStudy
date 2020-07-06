package com.ktao.threadlocal_;

import java.util.concurrent.TimeUnit;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/

public class ThreadLocal_01 {

    static class Person {
        public String name = "张三";
    }

    volatile static Person p = new Person();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "--" + p.name);
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "李四";
            System.out.println(Thread.currentThread() + "--" + p.name);
        }).start();
    }
}
