package com.ktao.reference;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 * 参考应用：WeakHashMap、ThreadLocal
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
@Slf4j
public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        log.info("m.get(): " + m.get());
        System.gc();
        log.info("m.get(): " + m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        // remove: 防止内存泄漏
        tl.remove();
    }
}
