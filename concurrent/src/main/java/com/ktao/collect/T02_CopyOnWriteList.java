package com.ktao.collect;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
@Slf4j
public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> list =
//                new ArrayList<>(); //这个会出并发问题！
//                new Vector(); // 线程安全(性能较差)
                new CopyOnWriteArrayList<>(); //线程安全

        Random R = new Random();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    list.add("a" + R.nextInt(10000));
                }
            });
        }
        runAndComputeTime(threads);
        System.out.println(list.size());
    }

    static void runAndComputeTime(Thread[] threads) {
        long s1 = System.currentTimeMillis();
        Arrays.asList(threads).forEach(Thread::start);
        Arrays.asList(threads).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }
}
