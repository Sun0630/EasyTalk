package com.sx.easytalk.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * @Author sunxin
 * @Date 2017/7/22 17:45
 * @Description Activity的基类
 */

public class BaseActivity extends AppCompatActivity {

    /**
     * 开启一个新的Activity
     *
     * @param clazz    开启的Activity的字节码
     * @param isFinish 是否销毁
     */
    public void startActivity(Class clazz, boolean isFinish) {
        startActivity(new Intent(this, clazz));
        if (isFinish) {
            finish();
        } else {

        }
    }

}
