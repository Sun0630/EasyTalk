package com.sx.easytalk.listener;

import com.hyphenate.EMCallBack;
import com.sx.easytalk.utils.ThreadUtils;

/**
 * @Author sunxin
 * @Date 2018/1/6 12:36
 * @Description
 */

public abstract class CallbackListener implements EMCallBack {

    public abstract void onMainSuccess();

    public abstract void onMainError(int code, String error);

    @Override
    public void onSuccess() {
        ThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                onMainSuccess();
            }
        });
    }

    @Override
    public void onError(final int code, final String error) {
        ThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                onMainError(code, error);
            }
        });
    }

    @Override
    public void onProgress(int progress, String status) {

    }
}
