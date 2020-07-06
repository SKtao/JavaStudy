package com.ktao.reference;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.SoftReference;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
@Slf4j
public class T02_SoftReference {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*1024]);
        System.out.println("GC前 m(软引用) = " + m.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("GC后 m(软引用) = " + m.get());
        //再分配一个数组，heap将装不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] b = new byte[1024*1024*1024];
        System.out.println("继续分配内存，为防止OOM, GC后的 m(软引用) = " + m.get());
    }
}
