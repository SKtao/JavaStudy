package com.ktao.lock_;

import com.ktao.lock_.T01_ReentrantLock1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.JDK 中提供的手工锁，reentrantLock（重入锁） 用来代替 synchronized，但必须要手动释放锁
 *
 * 2.使用 synchronized 锁定如果遇到异常的话，jvm 会自动释放锁，但是 lock 必须手动释放锁，因此经常在 finally 中进行锁的释放
 *   synchronized 锁由 jvm 进行管理,手动上锁，自动释放
 **/
public class T02_ReentrantLock2 {
    private Lock lock = new ReentrantLock();

    void m1() {
        System.out.println("...m1()...");
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
        try {
            lock.lock();
            System.out.println("...m2()...");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 t1 = new T01_ReentrantLock1();
        new Thread(t1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
