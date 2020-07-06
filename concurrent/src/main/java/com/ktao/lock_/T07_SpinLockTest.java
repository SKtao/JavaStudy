package com.ktao.lock_;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自定义自旋锁(cas)
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class T07_SpinLockTest {

    static class SpinLock {
        private AtomicReference<Thread> atomicReference = new AtomicReference<>();

        public void lock() {
            Thread thread = Thread.currentThread();
            while (!atomicReference.compareAndSet(null, thread)) {

            }
        }

        public void release() {
            Thread thread = Thread.currentThread();
            atomicReference.compareAndSet(thread, null);
        }
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        AtomicBoolean flag = new AtomicBoolean(true);
        new Thread(() -> {
            // 加锁
            spinLock.lock();
            System.out.println(Thread.currentThread() + "flag: " + flag);
            try {
                Thread.sleep(1000);
                flag.set(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                spinLock.release();
            }
        }, "thread-1").start();

        new Thread(() -> {
            // 加锁
            spinLock.lock();
            System.out.println(Thread.currentThread() + "flag: " + flag);
            // 释放锁
            spinLock.release();
        }, "thread-2").start();

    }
}
