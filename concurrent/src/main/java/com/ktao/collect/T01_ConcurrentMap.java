package com.ktao.collect;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class T01_ConcurrentMap {

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = new ConcurrentHashMap<>();
//        Map<String, String> map = new ConcurrentSkipListMap<>(); //高并发并且排序

        //Map<String, String> map = new Hashtable<>();
        //Map<String, String> map = new HashMap<>(); //Collections.synchronizedXXX
        Random R = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 1; j <= 10000; j++) {
                    map.put("a" + R.nextInt(100000), "a" + R.nextInt(100000));
                }
                latch.countDown();
            });
        }
        Arrays.asList(threads).forEach(Thread::start);
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(map.size());
    }
}
