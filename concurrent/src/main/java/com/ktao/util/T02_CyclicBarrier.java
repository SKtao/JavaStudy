package com.ktao.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier(栅栏)，可用于限流
 * 复杂操作:
 *   1.数据库
 *   2.网络
 *   3.文件
 **/
@Slf4j
public class T02_CyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, () -> log.info("满人(容量20)"));
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= 100; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    log.info("房间人数: {}", finalI);
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
