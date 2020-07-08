package com.ktao.code_problem.A1B2C3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class T3_BlockingQueue {
    static char[] A = "1234567".toCharArray();
    static char[] B = "ABCDEFG".toCharArray();

    static BlockingQueue<String> q1 = new ArrayBlockingQueue(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue(1);

    public static void main(String[] args) {
        new Thread(() -> {
            for(char c : A) {
                try {
                    System.out.print(c);
                    q1.put("ok");
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();

        new Thread(() -> {
            for(char c : B) {
                try {
                    q1.take();
                    System.out.print(c);
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t2").start();
    }
}
