package com.sx.easytalk.presenter;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;
import com.sx.easytalk.utils.ThreadUtils;
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
        EMClient.getInstance().login(userName, pwd, new EMCallBack() {
            @Override
            public void onSuccess() {
                Logger.d("环信登陆成功");
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.onLogin(userName,pwd,true,"登陆成功");
                    }
                });
            }

            @Override
            public void onError(int code, final String error) {
                Logger.d("环信登陆失败");
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.onLogin(userName,pwd,false,error);
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }
}
