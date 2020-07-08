package com.ktao.synchronized_;

import java.util.concurrent.TimeUnit;

/**
 * 演示父子类可重入性
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class T4 {
    synchronized void m() {
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        new SubT4().m();
    }
}


class SubT4 extends T4 {
    @Override
    synchronized void m() {
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }
}