package com.ktao.unsafe_;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/5
 **/
public class TestUnsafe {
    private int i = 0;
    private static TestUnsafe t = new TestUnsafe();

    public static void main(String[] args) throws Exception {
        //Unsafe unsafe = Unsafe.getUnsafe();

        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        Field f = TestUnsafe.class.getDeclaredField("i");
        long offset = unsafe.objectFieldOffset(f);
        System.out.println(offset);

        boolean success = unsafe.compareAndSwapInt(t, offset, 0, 1);
        System.out.println(success);
        System.out.println(t.i);
        //unsafe.compareAndSwapInt()
    }
}
