package com.ktao.classlayout;

import lombok.Data;
import org.openjdk.jol.info.ClassLayout;

/**
 * 通过jol工具查看对象的内存布局
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/5
 **/

// 面试题：对象在内存中的内存布局
class Test_ClassLayout {

    @Data
    static class User {
        private String name;
        private Integer age;
    }

    public static void main(String[] args) {
//        Object o = new Object();
        User o = new User();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        /**
         * com.ktao.classlayout.Test_ClassLayout$User object internals:
         *  OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
         *       0     4                     (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4                     (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                     (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
         *      12     4    java.lang.String User.name                                 null
         *      16     4   java.lang.Integer User.age                                  null
         *      20     4                     (loss due to the next object alignment)
         * Instance size: 24 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */
    }
}
