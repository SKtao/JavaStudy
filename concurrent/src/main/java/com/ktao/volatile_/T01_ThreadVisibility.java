package com.ktao.volatile_;

/**
 * 线程可见性
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class T01_ThreadVisibility {
    /** volatile: 保证主线程修改flag后，子线程能够感知到*/
    private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()-> {
            while (flag) {
//                System.out.println("... do something ...");
            }
            System.out.println("end");
        }, "server").start();
        Thread.sleep(1000);
        flag = false;
    }
}