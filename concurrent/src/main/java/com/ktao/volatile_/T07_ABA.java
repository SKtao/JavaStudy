package com.ktao.volatile_;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *  通过 AtomicStampedReference 类解决 ABA 问题
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class T07_ABA {
    /**
     * AtomicReference: 一个可以原子读写的对象引用变量(CAS: 可能出现ABA问题)
     * AtomicStampedReference：AtomicReference的升级版，内部不仅维护对象值，还维护了一个时间戳(类似version的东西)
     */
    static class T {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        // 版本从1开始
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("============= 演示 ABA 问题 ============");
        doABA();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("============= 解决 ABA 问题 ============");
        resolveABA();
    }

    /**
     * 解决ABA问题
     */
    private static void resolveABA() {
        T t = new T();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 目前的版本号：" + t.atomicStampedReference.getStamp());
            try {
                System.out.println("... do something for 1 seconds ... ");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("... 修改atomicStampedReference的值：100 -> 101 ...");
            t.atomicStampedReference.compareAndSet(100, 101,
                    t.atomicStampedReference.getStamp(), t.atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 目前的版本号：" + t.atomicStampedReference.getStamp());
            System.out.println("... 修改atomicStampedReference的值：101 -> 100 ...");
            t.atomicStampedReference.compareAndSet(101, 100,
                    t.atomicStampedReference.getStamp(), t.atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 目前的版本号：" + t.atomicStampedReference.getStamp());
        }, "thread---2").start();

        new Thread(() -> {
            System.out.println("... thread---4 start ... ");
            // 开始拿到版本号
            int stamp = t.atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 目前的版本号：" + t.atomicStampedReference.getStamp());
            try {
                System.out.println("... do something for 3 seconds ... ");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("... do something end ... ");
            // true
//            boolean flag = t.atomicStampedReference.compareAndSet(100, 2019,
//                    t.atomicStampedReference.getStamp(),  t.atomicStampedReference.getStamp() + 1);
            // false
            boolean flag = t.atomicStampedReference.compareAndSet(100, 2019,
                    stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + " 是否执行成功：" + flag + "\t 目前共享资源的值：" + t.atomicStampedReference.getReference());
        }, "thread---3").start();
    }

    /**
     * 演示ABA问题
     */
    private static void doABA() {
        T t = new T();
        new Thread(() -> {
            // 100 => 101 => 100
            t.atomicReference.compareAndSet(100, 101);
            t.atomicReference.compareAndSet(101, 100);
        }, "thread---1").start();

        new Thread(() -> {
            try {
                System.out.println("do something for 1 seconds ... ");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 若atomicReference被成功修改为100后，则执行成功
            System.out.println("是否执行成功：" + t.atomicReference.compareAndSet(100, 2019) + "\t 目前共享资源的值：" + t.atomicReference.get());
        }, "thread---1").start();
    }
}
