package com.ktao.gc;

/**
 * 长期存活的对象将进入老年代
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/14
 **/
public class T3_TenuringThresholdTest {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM Options: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
     * -XX:+PrintTenuringDistribution
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }
}
