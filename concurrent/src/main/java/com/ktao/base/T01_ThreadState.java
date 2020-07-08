package com.ktao.base;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class T01_ThreadState {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(this.getState()); //RUNNABLE
            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();
        System.out.println(t.getState()); //NEW
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getState()); //TERMINATED

    }
}
