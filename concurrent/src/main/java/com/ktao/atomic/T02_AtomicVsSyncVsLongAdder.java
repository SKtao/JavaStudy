package com.ktao.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * atomic对象 vs synchronized vs longAdder
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/7
 **/
public class T02_AtomicVsSyncVsLongAdder {
    /**
     * Atomic: 100000000 time: 1871
     * Sync: 100000000 time: 1207
     * LongAdder: 100000000 time: 371
     */
    public static final Integer LOOP_NUM = 100000;
    static AtomicLong count1 = new AtomicLong(0L);
    static long count2 = 0L;
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        // Atomic测试
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < LOOP_NUM; k++) {
                    count1.incrementAndGet();
                }
            });
        }
        long start = System.currentTimeMillis();
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        long end = System.currentTimeMillis();
        System.out.println("Atomic: " + count1.get() + " time: " + (end - start));
        // Sync测试
        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < LOOP_NUM; k++) {
                    synchronized (lock) {
                        count2++;
                    }
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        end = System.currentTimeMillis();
        System.out.println("Sync: " + count2 + " time: " + (end - start));
        // LongAdder测试
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < LOOP_NUM; k++) {
                    count3.increment();
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        end = System.currentTimeMillis();
        System.out.println("LongAdder: " + count2 + " time: " + (end - start));
    }
}
