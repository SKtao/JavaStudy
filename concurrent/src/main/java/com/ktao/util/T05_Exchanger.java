package com.ktao.util;

import java.util.concurrent.Exchanger;

/**
 * Exchanger类可用于两个线程之间交换信息。可简单地将Exchanger对象理解为一个包含两个格子的容器，
 * 通过exchanger方法可以向两个格子中填充信息。当两个格子中的均被填充时，该对象会自动将两个格子的信息交换，
 * 然后返回给线程，从而实现两个线程的信息交换
 */
public class T05_Exchanger {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            String msg = "T1";
            try {
                msg = exchanger.exchange(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":" + msg);
        }, "Thread1").start();

        new Thread(() -> {
            String msg = "T2";
            try {
                msg = exchanger.exchange(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":" + msg);
        }, "Thread2").start();
    }
}
