package com.sx.easytalk.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import com.sx.easytalk.MainActivity;
import com.sx.easytalk.R;
import com.sx.easytalk.adapter.AnimatorListenerAdapter;
import com.sx.easytalk.presenter.ISplashPresenter;
import com.sx.easytalk.presenter.SplashPresenterImpl;

public class SplashActivity extends BaseActivity implements ISplashView {


    private ImageView iv_splash;

    private ISplashPresenter mSplashPresenter;

    /*
    * 闪屏页面逻辑
    *   1，判断有没有登录(P层)
    *       如果登录了，直接进入MainActivity (V层)
    *       如果没有登录，两秒后进入LoginActivity(V层)
    *
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
    }

    private void initData() {
        mSplashPresenter = new SplashPresenterImpl(this);
        mSplashPresenter.isLogin();
    }

    private void initView() {
        iv_splash = (ImageView) findViewById(R.id.iv_splash);
    }

    @Override
    public void onCheckLogined(boolean isLogin) {
        if (isLogin) {
            //已经登录，跳转MainActivity
            startActivity(MainActivity.class, true);
        } else {
            //没有登录，闪屏页持续一个2s的透明度动画然后跳转LoginActivity
            ObjectAnimator animator = ObjectAnimator
                    .ofFloat(iv_splash, "alpha",0,1)
                    .setDuration(2000);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    startActivity(LoginActivity.class, true);
                }
            });
            animator.start();

        }
    }
}
