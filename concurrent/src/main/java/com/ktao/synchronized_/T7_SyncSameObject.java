package com.ktao.synchronized_;

import java.util.concurrent.TimeUnit;

/**
 * 更改锁的引用对象
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class T7_SyncSameObject {

    /*final*/ Object o = new Object();

    void m() {
        synchronized(o) {
            while(true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());


            }
        }
    }

    public static void main(String[] args) {
        T7_SyncSameObject t = new T7_SyncSameObject();
        //启动第一个线程
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建第二个线程
        Thread t2 = new Thread(t::m, "t2");
        t.o = new Object(); //锁对象发生改变，所以t2线程得以执行，如果注释掉这句话，线程2将永远得不到执行机会
        t2.start();

    }
}
