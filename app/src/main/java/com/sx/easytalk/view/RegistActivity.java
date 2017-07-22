package com.sx.easytalk.view;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sx.easytalk.R;
import com.sx.easytalk.presenter.RegistPresenter;
import com.sx.easytalk.presenter.RegistePresenterImpl;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, RegistView {

    private EditText et_username;
    private TextInputLayout til_username;
    private EditText et_pwd;
    private TextInputLayout til_pwd;
    private Button btn_login;

    private RegistPresenter mRegistPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        mRegistPresenter = new RegistePresenterImpl(this);
    }

    private void initListener() {
        //为密码输入框绑定键盘监听事件
        et_pwd.setOnEditorActionListener(this);
    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        til_username = (TextInputLayout) findViewById(R.id.til_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        til_pwd = (TextInputLayout) findViewById(R.id.til_pwd);
        btn_login = (Button) findViewById(R.id.btn_regist);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_regist:
                submit();
                break;
        }
    }

    /**
     * 注册
     *
     * @param username
     * @param pwd
     */
    private void regist(String username, String pwd) {
        mRegistPresenter.login(username, pwd);
    }

    private void submit() {
        // validate
        String username = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "username不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "pwd不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        regist(username, pwd);

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v.getId() == R.id.et_pwd) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {//当点击键盘的下一步时
                submit();//注册
                return true;
            }
        }
        return false;
    }
}
