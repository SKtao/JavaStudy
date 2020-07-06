package com.ktao.lock_;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 模拟一个缓存，验证读写分离，也就是共享锁的重要性
 * 1.一个缓存的底层是用 Map 实现的，主要有读，写，清空三个操作
 * 2.为什么加读写分离的锁？提高效率，多个读线程同时进行并不影响效率
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
@Slf4j
public class T05_ReentrantReadWriteLock {
    /**
     * 模拟锁缓存
     */
    static class Cache {
        Map<Integer, Object> map = new HashMap<>();
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void put(int key, Object obj) throws Exception{
            // 加写锁
            readWriteLock.writeLock().lock();
            try {
                log.info("{} 正在写入key-value: {}, {}", Thread.currentThread().getName(), key, obj);
                TimeUnit.SECONDS.sleep(1);
                map.put(key, obj);
                log.info("{} 写入完成!", Thread.currentThread().getName());
            } finally {
                // 释放写锁
                readWriteLock.writeLock().unlock();
            }
        }

        public void get(int key) throws Exception{
            // 加读锁
            readWriteLock.readLock().lock();
            Thread.sleep(1);
            log.info("{} 正在读取: ",Thread.currentThread().getName());
            Object obj = map.get(key);
            log.info("{} 读取完成：{}", Thread.currentThread().getName(), obj);
        }
    }

    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 1; i <= 5; i++) {
            int key = i, value = i;
            new Thread(() -> {
                try {
                    cache.put(key,value);
                } catch (Exception e) {
                    log.error("thread error...");
                }
            }, "thread--" + i).start();
        }

        for (int i = 1; i <= 5; i++) {
            int key = i;
            new Thread(() -> {
                try {
                    cache.get(key);
                } catch (Exception e) {
                    log.error("thread error...");
                }
            }, "thread--" + i).start();
        }
    }
}
