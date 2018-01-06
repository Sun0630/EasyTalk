package com.sx.easytalk.presenter.impl;

import com.hyphenate.chat.EMClient;
import com.sx.easytalk.listener.CallbackListener;
import com.sx.easytalk.presenter.LogoutPresenter;
import com.sx.easytalk.view.fragment.PluginView;

/**
 * @Author sunxin
 * @Date 2018/1/6 12:30
 * @Description
 */

public class LogoutPresenterImpl implements LogoutPresenter {

    private PluginView mPluginView;

    public LogoutPresenterImpl(PluginView pluginView) {
        mPluginView = pluginView;
    }

    @Override
    public void logout() {
        EMClient.getInstance().logout(true, new CallbackListener() {
            @Override
            public void onMainSuccess() {
                mPluginView.onLogout(EMClient.getInstance().getCurrentUser(), true, null);
            }

            @Override
            public void onMainError(int code, String error) {
                mPluginView.onLogout(EMClient.getInstance().getCurrentUser(), false, error);
            }
        });
    }
}
