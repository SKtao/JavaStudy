package com.ktao.volatile_;

import java.util.concurrent.TimeUnit;

/**
 * 单例模式
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class T06_SingletonPattern {

    /**
     * 单线程下的单例模式
     */
    static class Single {
        private static Single single = null;

        private Single() {
            System.out.println("Single Construct!");
        }

        public static Single getSingleInstance() {
            if (single == null) {
                single = new Single();
            }
            return single;
        }
    }

    /**
     * 多线程下的单例模式
     */
    static class SingleConcurrent {
        private static volatile SingleConcurrent instance = null;

        public SingleConcurrent() {
            System.out.println("SingleConcurrent Construct!");
        }

        public static SingleConcurrent getSingleton() {
            if (instance == null) {
                synchronized (SingleConcurrent.class) {
                    if (instance == null) {
                        /**
                         * 下面代码可能发生指令重排
                         *  memory = allocate() // 1 分配对象内存空间
                         *  instance(memory)    // 2 初始化对象
                         *  instance = memory   // 3 设置 instance 指向刚分配的内存地址，此时 instance != null
                         *  2 3 可能会调换顺序，导致有的线程拿到的地址不为空，可是指向的对象为空
                         */
                        instance = new SingleConcurrent();
                    }
                }
            }
            return instance;
        }

        public static void main(String[] args) {
            // 验证单线程模式下的单例模式
            System.out.println("单线程下验证：-----------");
            System.out.println(Single.getSingleInstance() == Single.getSingleInstance());
            System.out.println(Single.getSingleInstance() == Single.getSingleInstance());
            System.out.println(Single.getSingleInstance() == Single.getSingleInstance());

            // 必须先将单线程的验证注释掉
            System.out.println("多线程下验证：-----------");
            for (int i = 1; i <= 10; i++) {
                new Thread(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Single.getSingleInstance());
                }, "thread---" + i).start();
            }

//            // 指令重排可能导致出错，怎么加 volatile ，为什么加？
//            System.out.println("双重锁多线程下验证：-----------");
//            for (int i = 1; i <= 10; i++) {
//                new Thread(() -> {
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    SingleConcurrent.getSingleton();
//                }, "thread---" + i).start();
//            }

        }
    }
}
