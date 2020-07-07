package com.ktao.threadpool_;

import java.util.concurrent.*;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/7
 **/
public class T01_ThreadPoolExecutor {
    private static Object o = new Object();
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executor.submit(() -> {
                try {
                    synchronized (o) {
                        BlockingQueue<Runnable> blockingQueue = executor.getQueue();
                        System.out.println("阻塞队列 size: " + blockingQueue.size());
                        int activeCount = (executor).getActiveCount();
                        System.out.println("当前活跃线程数: " + activeCount);
                        long taskCount = (executor).getTaskCount();
                        System.out.println("当前任务数: " + taskCount);
                        long completedTaskCount = (executor).getCompletedTaskCount();
                        System.out.println("当前已完成任务数: " + completedTaskCount);
                        System.out.println(finalI);
                    }
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
