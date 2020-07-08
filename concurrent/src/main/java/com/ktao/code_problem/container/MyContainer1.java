package com.ktao.code_problem.container;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：实现一个容器，提供两个方法add，size；两个线程，线程1添加10个元素到容器中，
 * 线程2实现监控元素的个数，当个数到5个，线程2给出提示并结束
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class MyContainer1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final static int MAX = 10;
    private int count = 0;

    // Q1.为什么需要加synchronized？
    // A1:++count（非原子操作）
    public synchronized void put(T t) {
        //Q2. 想想为什么用while而不是用if？
        //A2. 若用if，当前线程被唤醒后不会再次判断lists.size() == MAX
        while (lists.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        // 通知消费者线程进行消费
        // notifyAll存在一些问题：可能会唤醒其他wait的线程，而仅仅是当前对应的consumer线程
        // 因此可以通过Condition来避免：MyContainer2
        this.notifyAll();
    }

    public synchronized T get() {
        T t = null;
        while(lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count --;
        this.notifyAll(); // 通知生产者进行生产
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
        // 启动消费者线程
        for(int i=0; i<10; i++) {
            new Thread(()->{
                for(int j=0; j<5; j++) System.out.println(c.get());
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启动生产者线程
        for(int i=0; i<2; i++) {
            new Thread(()->{
                for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }
}
