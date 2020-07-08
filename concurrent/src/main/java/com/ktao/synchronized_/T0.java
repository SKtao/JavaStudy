package com.ktao.synchronized_;

/**
 * synchronized几种同步方式
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class T0 {
    private Object object = new Object();

    /**
     * 修饰方法，当前对象作为同步锁
     */
    synchronized void m1() {
        //等同于在方法的代码执行时要synchronized(this)
    }

    /**
     * 修饰this，当前这个对象作为同步锁
     */
    void m2() {
        synchronized (this) {
        }
    }

    /**
     * 修饰一个object，object此时作为同步锁
     */
    void m3() {
        synchronized (object) {

        }
    }

    /**
     * 修饰static方法，这个类的Class对象作为同步锁
     */
    static synchronized void m4() {
        //这里等同于synchronized(T0.class)
    }

    /**
     * 修饰类对象(.class)
     */
    static void m5() {
        synchronized (T0.class) { //考虑一下这里写synchronized(this)是否可以？

        }
    }

    /**
     * synchornized实现过程：
     *
     * 1. java代码：synchronized
     * 2. monitorenter monitorexit [jvm]
     * 3. 执行过程中自动升级
     * 4. lock comxchg [汇编指令层]
     */
    public static void main(String[] args) {

    }
}
