package com.ktao.code_problem.A1B2C3;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class T2_CAS {

    enum ReadyToRun {T1, T2};

    static volatile ReadyToRun r = ReadyToRun.T1;

    static char[] A = "1234567".toCharArray();
    static char[] B = "ABCDEFG".toCharArray();

    public static void main(String[] args) {
        new Thread(() -> {
            for (char c : A) {
                while (r != ReadyToRun.T1) {} // 自旋
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : B) {
                while (r != ReadyToRun.T2) {} // 自旋
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();
    }
}
