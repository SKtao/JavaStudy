package com.ktao.synchronized_;

/**
 * 字符串作为synchronized锁存在的坑
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class T6_DoNotLockString {
    String s1 = "Hello";
    String s2 = "hello";
//    String a = "hello";

    void m1() {
        synchronized (s1) {
            System.out.println("m1");
        }
    }

    void m2() {
        synchronized (s2) {
            m1();
            System.out.println("m2");
        }
    }

    public static void main(String[] args) {
        T6_DoNotLockString t = new T6_DoNotLockString();
        new Thread(t::m2).start();
    }
}
