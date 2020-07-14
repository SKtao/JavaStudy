package com.ktao.gc;

/**
 * 动态对象年龄判定
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/14
 **/
public class T4_TenuringThresholdTest2 {
    /**
     * VM Options: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     * -XX:+PrintTenuringDistribution
     */
    private static final int _1MB = 1024 * 1024;

    /**
     * 为了能更好地适应不同程序的内存状况，HotSpot并不少永远要求对象的年龄必须达到MaxTenuringThreshold才晋升老年代，如果Survivor空间中
     * 相同年龄所有对象大小的总和大于Survivor空间的一半，则无须等到MaxTenuringThreshold要求的年龄
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4]; // allocation1 + allocation2大于survior空间一半
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }
}
