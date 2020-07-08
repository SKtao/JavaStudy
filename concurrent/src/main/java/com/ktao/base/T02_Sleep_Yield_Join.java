package com.ktao.base;

import java.util.concurrent.TimeUnit;

/**
 * Sleep、Yield、Join
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class T02_Sleep_Yield_Join {

    private static Object lock = new Object();
    private static int count = 0;
    /**
     * Sleep：
     * Thread.sleep(0)：触发操作系统立刻重新进行一次CPU竞争，重新计算优先级
     */
    static void testSleep() throws InterruptedException {
        /**
         * A休眠10s... -> A被唤醒... -> B休眠10s... -> B被唤醒...
         * (不会存在AB交叉情况，说明sleep并不会释放锁(Jvm：monitor，Kernel：LOCK指令))
         */
        new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("A休眠10s...");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("A被唤醒...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("B休眠10s...");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("B被唤醒...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Yield:
     */
    static void testYield() {
        new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println("A：" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println("B：" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    /**
     * Join：
     */
    static void testJoin() {
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 10; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < 10; i++) {
                System.out.println("B" + i);
                try {
                    Thread.sleep(500);
                    //TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }

    public static void main(String[] args) throws InterruptedException {
        testSleep();
//        testYield();
//        testJoin();
    }
}
