package com.sx.easytalk.presenter.impl;

import com.hyphenate.chat.EMClient;
import com.sx.easytalk.listener.CallbackListener;
import com.sx.easytalk.presenter.LoginPresenter;
import com.sx.easytalk.view.LoginView;

/**
 * @Author sunxin
 * @Date 2017/12/22 22:42
 * @Description
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView mLoginView;

    public LoginPresenterImpl(LoginView loginView) {
        mLoginView = loginView;
    }

    @Override
    public void login(final String userName, final String pwd) {
        //登陆环信
        EMClient.getInstance().login(userName, pwd, new CallbackListener() {
            @Override
            public void onMainSuccess() {
                mLoginView.onLogin(userName, pwd, true, "登陆成功");
            }

            @Override
            public void onMainError(int code, String error) {
                mLoginView.onLogin(userName, pwd, false, error);
            }
        });
    }
}
