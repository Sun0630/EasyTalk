package com.sx.easytalk.presenter.impl;

import com.hyphenate.chat.EMClient;
import com.sx.easytalk.presenter.ISplashPresenter;
import com.sx.easytalk.view.ISplashView;

/**
 * @Author sunxin
 * @Date 2017/7/22 17:26
 * @Description 实现类
 */

public class SplashPresenterImpl implements ISplashPresenter {

    private ISplashView mSplashView;

    public SplashPresenterImpl(ISplashView view) {
        mSplashView = view;
    }

    /**
     * 判断是否登录
     */
    @Override
    public void isLogin() {
        //使用环信SDK判断是否登录
        if (EMClient.getInstance().isLoggedInBefore() && EMClient.getInstance().isConnected()) {
            //已经登录，需要回调view层
            mSplashView.onCheckLogined(true);
        } else {
            //没有登录，需要回调view层
            mSplashView.onCheckLogined(false);
        }
    }
}
