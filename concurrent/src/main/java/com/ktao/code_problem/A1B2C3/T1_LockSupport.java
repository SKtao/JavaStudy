package com.ktao.code_problem.A1B2C3;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport实现
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class T1_LockSupport {
    static char[] A = "1234567".toCharArray();
    static char[] B = "ABCDEFG".toCharArray();

    static Thread t1;
    static Thread t2;

    public static void main(String[] args) {
       t1 =  new Thread(() -> {
            for(char c : A) {
                System.out.print(c);
                LockSupport.unpark(t2); //叫醒T2
                LockSupport.park(); //T1阻塞
            }
        }, "t1");

        t2 = new Thread(() -> {
            for(char c : B) {
                LockSupport.park(); //t2阻塞
                System.out.print(c);
                LockSupport.unpark(t1); //叫醒t1
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
