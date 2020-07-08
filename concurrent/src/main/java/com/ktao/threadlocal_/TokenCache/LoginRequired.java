package com.ktao.threadlocal_.TokenCache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
    /**
     * 是否需要设置用户信息
     * @return
     */
    boolean needSetLoginUser() default true;

    /**
     * 是否需要验证URL权限
     * @return
     */
    boolean checkUrlPermission() default false;
}
