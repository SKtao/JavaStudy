package com.ktao.threadlocal_;

import java.util.concurrent.TimeUnit;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class ThreadLocal_02 {
    static class Person {
        public String name = "张三";
    }
    static ThreadLocal<Person> p = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "--" + p.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 每个线程中有自己的变量，每个线程中拷贝了一份，改的都是自己的那份
            p.set(new Person());
            System.out.println(Thread.currentThread() + "--" + p.get());
        }).start();
    }
}
