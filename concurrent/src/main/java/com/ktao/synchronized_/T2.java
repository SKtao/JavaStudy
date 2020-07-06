package com.ktao.synchronized_;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class T2 {
    static volatile int i = 0;
    // 无需加锁
    public static void n() { i++; }

    /**
     * synchronized修饰，底层通过lock cmpxchg指令进行加锁（lock + cas）
     * cmpxchg：
     *   -用于比较并交换操作数，CPU对CAS的原语支持
     *   -非原子性，最早用于单核CPU
     * lock: CPU保证被其修饰的指令的原子性
     * 注：单核不需要加lock，多核需要加lock保证原子性
     */
    public static synchronized void m() {
        /**
         *   0x00000001139a57f0: lock cmpxchg %rbx,(%rsi)
         *   ......
         *   0x00000001139a5811: lock cmpxchg %rbx,(%rsi)
         */
    }

    public static void main(String[] args) {
        for(int j=0; j<1000_000; j++) {
            m();
            n();
        }
    }
}
