package com.ktao.synchronized_;

import java.util.concurrent.TimeUnit;

/**
 * 演示synchronized可重入性
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class T3 {
    //思考：为什么要设计成可重入？如果不可重入会带来哪些问题？
    synchronized void m1() {
        System.out.println("m1 start...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end...");
    }

    synchronized void m2() {
        System.out.println("m2 start...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end...");
    }

    public static void main(String[] args) {
        new T3().m1();
    }
}
