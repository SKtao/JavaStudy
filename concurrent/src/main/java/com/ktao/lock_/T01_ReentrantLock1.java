package com.ktao.lock_;

import java.util.concurrent.TimeUnit;

/**
 * synchronized本身就是可重入锁
 * 锁必须可重入：例如子类synchronized方法调用父类synchronized方法
 **/
public class T01_ReentrantLock1 {

    /**
     * 验证可重入性
     */
    synchronized void m1() {
        System.out.println("...m1()...");
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            // 当i == 2时，重入调用m2
            if (i == 2) {
                m2();
            }
        }
    }

    synchronized void m2() {
        System.out.println("...m2()...");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 t1 = new T01_ReentrantLock1();
        new Thread(t1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
