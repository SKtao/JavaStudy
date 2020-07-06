package com.ktao.volatile_;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile不能保证原子性(++非原子操作)
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class T05_VolatileNotSync {

    private volatile int count = 0;

    // synchronized加锁可以保证原子性
    public /*synchronized */ void add() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T05_VolatileNotSync t = new T05_VolatileNotSync();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(t::add, "thread-"+i));
        }
        threadList.forEach(Thread::start);

        // 等所有add线程执行完再继续执行主线程
        threadList.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
