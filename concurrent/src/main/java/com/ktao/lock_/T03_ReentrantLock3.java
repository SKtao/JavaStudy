package com.ktao.lock_;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.JDK 中提供的手工锁，reentrantLock（重入锁） 用来代替 synchronized，但必须要手动释放锁
 *
 * 2.使用 synchronized 锁定如果遇到异常的话，jvm 会自动释放锁，但是 lock 必须手动释放锁，因此经常在 finally 中进行锁的释放
 *   synchronized 锁由 jvm 进行管理,手动上锁，自动释放
 *
 * 3.使用 ReentrantLock 可以进行尝试锁定“”"tryLock"，或者在指定时间内无法锁定，线程可以决定是否继续等待
 **/
public class T03_ReentrantLock3 {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        boolean locked = false;
        try {
            // 等待锁等 5 秒，如果没有等到就继续往下执行
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2 ..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T03_ReentrantLock3 reentrantLock2 = new T03_ReentrantLock3();
        new Thread(reentrantLock2::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(reentrantLock2::m2).start();
    }
}
