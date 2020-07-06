package com.ktao.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch使用
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
@Slf4j
public class T01_TestCountDownLatch {
    public static void main(String[] args) {
        usingCountDownLatch();
        usingJoin();
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[10];
        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j = 1; j <= 100; j++) {
                    result += j;
                }
                log.info("计算完成：result = {}", result);
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("END JOIN");
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[10];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 1; j <= 100; j++) {
                    result += j;
                }
                log.info("计算完成：result = {}", result);
                latch.countDown();
            });
        }

        for (int i = 0; i < threads.length; i++) threads[i].start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("END COUNTDOWN");
    }
}
