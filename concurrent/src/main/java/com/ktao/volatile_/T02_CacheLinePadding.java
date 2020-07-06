package com.ktao.volatile_;

/**
 * 缓存行对齐（https://tech.meituan.com/2016/11/18/disruptor.html）
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class T02_CacheLinePadding {
    /**
     * 8*7 = 56字节(用于缓存行对齐填充)
     */
    private static class Padding {
        public volatile long p1, p2, p3, p4, p5, p6, p7; //
    }

    /**
     *【缓存行对齐】8 + 56 = 64字节(64位系统，缓存行大小为64字节)
     */
    private static class T extends Padding {
        public volatile long x = 0L;
    }

    /**
     *【不关心缓存行对齐：执行时间较长】
     */
//    private static class T{
//        public volatile long x = 0L;
//    }
    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }
}
