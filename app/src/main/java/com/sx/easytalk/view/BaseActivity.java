package com.sx.easytalk.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sx.easytalk.utils.Contant;
import com.sx.easytalk.utils.ToastUtils;

/**
 * @Author sunxin
 * @Date 2017/7/22 17:45
 * @Description Activity的基类
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        //设置不可取消
        mProgressDialog.setCancelable(false);
        mPreferences = getSharedPreferences(Contant.SP_NAME, MODE_PRIVATE);
    }


    /**
     * 保存用户名和密码
     *
     * @param userName
     * @param pwd
     */
    public void saveUser(String userName, String pwd) {
        mPreferences.edit()
                .putString(Contant.SP_USER_NAME, userName)
                .putString(Contant.SP_PWD, pwd)
                .apply();
    }

    public String getUserName() {
        return mPreferences.getString(Contant.SP_USER_NAME, "");
    }

    public String getPwd() {
        return mPreferences.getString(Contant.SP_PWD, "");
    }

    public void showDialog(String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    public void hideDialog() {
        mProgressDialog.dismiss();
    }

    public void showToast(String msg) {
        ToastUtils.showToast(this, msg);
    }

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
