package com.ktao.threadlocal_.TokenCache;

import org.apache.commons.lang3.StringUtils;

/**
 * @author kongtao
 * @version 1.0
 * @description:
 * @date 2020/7/8
 **/
public class LoginTokenContext {
    private String token;

    private final static ThreadLocal<LoginTokenContext> holder = ThreadLocal.withInitial(LoginTokenContext::new);

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 创建并保存token
     * @param token
     */
    public static void set(String token) {
        LoginTokenContext context = new LoginTokenContext();
        context.setToken(token);
        holder.set(context);
    }

    public static LoginTokenContext get() {
        return holder.get();
    }

    public static void remove() {
        holder.remove();
    }

    /**
     * 判断是否登录。标准：isNotBlank(getUserName())
     * @return
     */
    public boolean isLogin() {
        try {
            return StringUtils.isNotBlank(token);
        } catch (Exception e) {
            return false;
        }
    }
}
