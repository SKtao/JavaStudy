package com.ktao.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch使用
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
@Slf4j
public class T01_CountDownLatch {
    private static final Integer THREADS = 10;

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
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(THREADS);
        for (int i = 0; i < THREADS; i++) {
            executorService.submit(() -> {
                int result = 0;
                for (int j = 1; j <= 100; j++) {
                    result += j;
                }
                log.info("计算完成：result = {}", result);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("END COUNTDOWN");
        executorService.shutdown();
    }
}
