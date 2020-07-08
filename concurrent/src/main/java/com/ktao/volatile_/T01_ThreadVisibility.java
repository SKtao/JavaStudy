package com.ktao.volatile_;

/**
 * 线程可见性
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class T01_ThreadVisibility {
    /** volatile: 保证主线程修改flag后，子线程能够感知到*/
    private static volatile boolean flag = true;

    /**
     * 0x000000010eae88ce: lock addl $0x0,(%rsp) 【该指令相当于内存屏障】
     * lock：将本处理器缓存写入内存，并且引起别的处理器或者别的内核无效化其内存
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(()-> {
            while (flag) {
//                System.out.println("... do something ...");
            }
            System.out.println("end");
        }, "server").start();
        Thread.sleep(1000);
        flag = false;
    }
}