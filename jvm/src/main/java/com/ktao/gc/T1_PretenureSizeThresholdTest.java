package com.ktao.gc;

/**
 * 大对象直接进入老年代
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/14
 **/
public class T1_PretenureSizeThresholdTest {
    private static final int _1MB = 1024 * 1024;
    /**
     * VM Options: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     * -XX:PretenureSizeThreshold: 只针对Serial和ParNew两款新生代收集器有效
     */
    public static void main(String[] args) {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }
}
