package com.sx.easytalk.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sx.easytalk.R;
import com.sx.easytalk.presenter.ISplashPresenter;
import com.sx.easytalk.presenter.SplashPresenterImpl;

public class SplashActivity extends AppCompatActivity implements ISplashView{


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
        if (isLogin){
            //已经登录，跳转MainActivity

        }else {
            //没有登录，跳转LoginActivity

        }
    }
}
