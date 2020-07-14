package com.ktao.gc;

/**
 * 对象优先在Eden区分配
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/14
 **/
public class T0_EdenAllocateTest {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM Options: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * Java堆大小：20M, 新生代10M,老年代10M, Eden : from : to = 8:1:1
     */
    public static void main(String[] args) {
        /**
         * [GC (Allocation Failure) [PSYoungGen: 6389K->624K(9216K【新生代6389K -> 624k】] 6389K->4728K(19456K), 0.0030414 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
         * Heap
         *  PSYoungGen      total 9216K, used 5041K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
         *   eden space 8192K【Eden区】, 53% used [0x00000007bf600000,0x00000007bfa50758,0x00000007bfe00000)
         *   from space 1024K【From区】, 60% used [0x00000007bfe00000,0x00000007bfe9c010,0x00000007bff00000)
         *   to   space 1024K【To区】, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
         *  ParOldGen       total 10240K, used 4104K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
         *   object space 10240K, 40% used [0x00000007bec00000,0x00000007bf002020,0x00000007bf600000)
         *  Metaspace       used 3296K, capacity 4496K, committed 4864K, reserved 1056768K
         *   class space    used 363K, capacity 388K, committed 512K, reserved 1048576K
         */
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        // allocation4: 触发Minor GC
        allocation4 = new byte[4 * _1MB];
    }
}
