package com.ktao.coding;

/**
 * 要求用线程顺序打印A1B2C3....Z26
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class Question1 {
    private static Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notifyAll();
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                for (char a = 'A'; a <= 'Z'; a++) {
                    System.out.print(a);
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notifyAll();
            }
        }).start();
    }
}
