package com.ktao.reference;

import java.io.IOException;

/**
 * 强引用
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/6
 **/
public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc(); //DisableExplicitGC
        System.in.read();
    }
}
