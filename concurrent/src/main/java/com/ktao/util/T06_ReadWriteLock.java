package com.ktao.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁（适用于读多写少）
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/7
 **/
@Slf4j
public class T06_ReadWriteLock {
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 读锁：读共享
    private static Lock readLock = readWriteLock.readLock();
    // 写锁：互斥锁(读写，写写)
    private static Lock writeLock = readWriteLock.writeLock();
    private static int value = 100;

    public static void read() {
        try {
            readLock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read over: value = " + value);
        } catch (InterruptedException e) {
            log.info("exception: ",e);
        } finally {
            readLock.unlock();
        }
    }

    public static void write(int num) {
        try {
            writeLock.lock();
            TimeUnit.SECONDS.sleep(1);
            value = num;
            System.out.println("write over: value = " + num);
        } catch (InterruptedException e) {
            log.info("exception: ",e);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable readR = () -> read();
        Runnable writeR = () -> write(new Random().nextInt());

        // 读多写少
        for (int i = 0; i < 20; i++) new Thread(readR).start();
        for (int i = 0; i < 5; i++) new Thread(writeR).start();
    }
}
