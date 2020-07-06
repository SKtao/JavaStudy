package com.ktao.synchronized_;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
@Slf4j
public class T1 {
    private int count = 10;
    private Object o = new Object();
    private Object x = new Object();

    public void m() {
        /**
         * 根据如下信息：synchronized的锁信息存储在对象头中(8字节)
         */
        synchronized (o) {
            count--;
            try {
                Thread.sleep(500);
                System.out.println("加锁对象o：" + ClassLayout.parseInstance(o).toPrintable());
                /**
                 * 加锁对象o：java.lang.Object object internals:
                 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
                 *       0     4        (object header)                           ba 38 01 41 (10111010 00111000 00000001 01000001) (1090599098)
                 *       4     4        (object header)                           92 7f 00 00 (10010010 01111111 00000000 00000000) (32658)
                 *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
                 *      12     4        (loss due to the next object alignment)
                 * Instance size: 16 bytes
                 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
                 */
                System.out.println("无锁对象x：" + ClassLayout.parseInstance(x).toPrintable());
                /**
                 * 无锁对象x：java.lang.Object object internals:
                 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
                 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
                 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
                 *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
                 *      12     4        (loss due to the next object alignment)
                 * Instance size: 16 bytes
                 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
                 */
            } catch (InterruptedException e) {
                log.info("thread.sleep exception: ", e);
            }
            log.info(Thread.currentThread().getName() + "count = " + count);
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        new Thread(t1::m).start();
//        new Thread(t1::m).start();
    }
}
