package com.ktao.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * atomic底层采用cas
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/7
 **/
public class T01_AtomicInteger {

    private AtomicInteger atomicCount = new AtomicInteger(0);

    private /*volatile*/ Integer count = 0;

    public void m() {
        for (int i = 0; i < 10000; i++) {
            atomicCount.incrementAndGet();
        }
    }

    public /*synchronized*/ void n() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {
                t.n();
                t.m();
            },"thread-" + i));
        }
        threads.forEach(Thread::start);
        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("count: " + t.count);
        System.out.println("atomicCount: " + t.atomicCount);
    }
}
