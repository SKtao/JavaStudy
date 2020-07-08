package com.ktao.synchronized_;

/**
 * 演示synchronized异常释放锁
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class T5 {
    private int count = 0;

    synchronized void m() {
        while (true) {
            System.out.println(Thread.currentThread() + "-count: " + count++);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 10) {
                // ArithmeticException异常，释放锁
                int i = count / 0;

//                // catch异常，不释放锁
//                int i = 0;
//                try {
//                    i = count / 0;
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        T5 t = new T5();
        new Thread(t::m).start();
        new Thread(t::m).start();
    }
}
