package com.sx.easytalk.view;

/**
 * @Author sunxin
 * @Date 2017/12/22 22:40
 * @Description 登陆的V层借口
 */

public interface LoginView {
    void onLogin(String userName, String pwd, boolean success, String errorMsg);
}
