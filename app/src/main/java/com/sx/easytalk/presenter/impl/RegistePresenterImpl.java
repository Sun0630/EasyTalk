package com.sx.easytalk.presenter.impl;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.sx.easytalk.model.User;
import com.sx.easytalk.presenter.RegistPresenter;
import com.sx.easytalk.utils.ThreadUtils;
import com.sx.easytalk.view.RegistView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

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


    /**
     * 注册逻辑
     *
     * @param username
     * @param pwd
     */
    @Override
    public void regist(final String username, final String pwd) {
        /**
         * 1.注册bomb
         * 2.bomb注册成功之后再去注册环信
         * 3.如果bomb成功了，但是环信失败了，就在去吧bomb的数据删除掉
         */

        User user = new User();
        user.setUsername(username);
        user.setPassword(pwd);

        user.signUp(new SaveListener<User>() {
            @Override
            public void done(final User user, BmobException e) {
                if (e == null) {
                    //bomb注册成功，再去注册环信平台
                    ThreadUtils.runOnSubThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                EMClient.getInstance().createAccount(username, pwd);
                                ThreadUtils.runOnMainThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mRegistView.onRegist(username, pwd, true, "regist success");
                                    }
                                });
                            } catch (final HyphenateException e1) {
                                e1.printStackTrace();
                                //环信注册失败,删除Bomb上的用户
                                user.delete();
                                ThreadUtils.runOnMainThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mRegistView.onRegist(username, pwd, false, e1.getMessage());
                                    }
                                });
                            }
                        }
                    });
                } else {
                    //注册失败,回调显示界面
                    mRegistView.onRegist(username, pwd, false, e.getMessage());
                }
            }
        });


    }

}
