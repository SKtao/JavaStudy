package com.ktao.lock_;

import java.util.concurrent.TimeUnit;

/**
 * 模拟死锁 -- 通过jstack能检测死锁
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class T06_DeadLockTest {
    static class T implements Runnable {
        private final Object lock1;
        private final Object lock2;

        public T(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "\t 持有锁 " + lock1 + "，尝试获得锁 " + lock2);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "\t 持有锁 " + lock2 + "，尝试获得锁 " + lock1);
                }
            }
        }
    }

    public static void main(String[] args) {
        /**
         * 【通过jstack查看死锁线程】:
         * Found one Java-level deadlock:
         * =============================
         * "thread---2":
         *   waiting to lock monitor 0x00007faf4c01fd58 (object 0x00000007957247d0, a java.lang.String),
         *   which is held by "thread---1"
         * "thread---1":
         *   waiting to lock monitor 0x00007faf4c01e8b8 (object 0x0000000795724808, a java.lang.String),
         *   which is held by "thread---2"
         *
         * Java stack information for the threads listed above:
         * ===================================================
         * "thread---2":
         *         at com.ktao.lock_.T01_DeadLock$T.run(T01_DeadLock.java:31)
         *         - waiting to lock <0x00000007957247d0> (a java.lang.String)
         *         - locked <0x0000000795724808> (a java.lang.String)
         *         at java.lang.Thread.run(Thread.java:748)
         * "thread---1":
         *         at com.ktao.lock_.T01_DeadLock$T.run(T01_DeadLock.java:31)
         *         - waiting to lock <0x0000000795724808> (a java.lang.String)
         *         - locked <0x00000007957247d0> (a java.lang.String)
         *         at java.lang.Thread.run(Thread.java:748)
         *
         * Found 1 deadlock.
         */
        String lock1 = "lock1";
        String lock2 = "lock2";
        new Thread(new T(lock1, lock2), "thread---1").start();
        new Thread(new T(lock2, lock1), "thread---2").start();
    }
}
