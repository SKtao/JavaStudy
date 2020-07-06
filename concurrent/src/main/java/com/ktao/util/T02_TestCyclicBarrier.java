package com.ktao.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier(栅栏)
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
@Slf4j
public class T02_TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, () -> log.info("满人(容量20)"));
        for (int i = 1; i <= 100; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    log.info("房间人数: {}", finalI);
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
