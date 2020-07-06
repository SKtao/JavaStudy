package com.ktao.collect.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 验证 SynchronousQueue 无容量队列的作用
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
@Slf4j
public class T01_SynchronousQueue {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            System.out.println("... thread-1 start ... ");
            try {
                TimeUnit.SECONDS.sleep(1);

                System.out.println(Thread.currentThread().getName() + "\t put aa");
                blockingQueue.put("aa");

                System.out.println(Thread.currentThread().getName() + "\t put bb");
                blockingQueue.put("bb");

                System.out.println(Thread.currentThread().getName() + "\t put cc");
                blockingQueue.put("cc");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-1").start();

        new Thread(() -> {
            System.out.println("... thread-2 start ... ");
            try {
                TimeUnit.SECONDS.sleep(1);

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take() + "\t 任务完成");

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take() + "\t 任务完成");

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take() + "\t 任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-2").start();
    }
}
