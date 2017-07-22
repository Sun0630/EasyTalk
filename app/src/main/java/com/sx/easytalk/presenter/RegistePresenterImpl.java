package com.sx.easytalk.presenter;

import com.sx.easytalk.view.RegistView;

/**
 * @Author sunxin
 * @Date 2017/7/22 23:36
 * @Description
 */

public class RegistePresenterImpl implements RegistPresenter {

    private RegistView mRegistView;

    public RegistePresenterImpl(RegistView registView) {
        mRegistView = registView;
    }

    @Override
    public void login(String username, String pwd) {

    }
}
