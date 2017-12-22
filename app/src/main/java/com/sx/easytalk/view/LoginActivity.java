package com.sx.easytalk.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sx.easytalk.MainActivity;
import com.sx.easytalk.R;
import com.sx.easytalk.presenter.LoginPresenter;
import com.sx.easytalk.presenter.LoginPresenterImpl;
import com.sx.easytalk.utils.StringUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener
        , TextView.OnEditorActionListener, LoginView {

    private EditText et_username;
    private TextInputLayout til_username;
    private EditText et_pwd;
    private TextInputLayout til_pwd;
    private Button btn_login;
    private TextView new_user;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();

    }

    private void initData() {
        //绑定
        mLoginPresenter = new LoginPresenterImpl(this);
        //数据回显
        et_username.setText(getUserName());
        et_pwd.setText(getPwd());
    }

    /**
     * 当再次startActivity的时候，接受一个新的Intent
     * 启动模式位SingleTop或者SingleTask的时候调用
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //数据回显
        et_username.setText(getUserName());
        et_pwd.setText(getPwd());
    }

    private void initView() {
        StatusBarUtil.setTransparent(this);
        et_username = (EditText) findViewById(R.id.et_username);
        til_username = (TextInputLayout) findViewById(R.id.til_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        til_pwd = (TextInputLayout) findViewById(R.id.til_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        new_user = (TextView) findViewById(R.id.new_user);

        btn_login.setOnClickListener(this);
        new_user.setOnClickListener(this);
        btn_login.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //登陆
                login();
                break;
            case R.id.new_user:
                startActivity(RegistActivity.class, false);
                break;
        }
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v.getId() == R.id.btn_login) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login();
            }
        }
        return false;
    }

    private void login() {
        //校验
        String username = et_username.getText().toString().trim();
        if (!StringUtils.checkUserName(username)) {
            til_username.setErrorEnabled(true);
            til_username.setError("用户名不合法");
            //将光标定位到用户名输入框右侧
            et_username.requestFocus(View.FOCUS_RIGHT);
            return;
        }

        String pwd = et_pwd.getText().toString().trim();
        if (!StringUtils.checkPwd(pwd)) {
            til_pwd.setErrorEnabled(true);
            til_pwd.setError("密码不合法");
            et_pwd.requestFocus(View.FOCUS_RIGHT);
            return;
        }

        showDialog("正在登录中...");
        mLoginPresenter.login(username, pwd);
    }

    @Override
    public void onLogin(String userName, String pwd, boolean success, String errorMsg) {
        hideDialog();
        if (success) {
            saveUser(userName, pwd);
            startActivity(MainActivity.class, true);
        } else {
            showToast("登陆失败：" + errorMsg);
        }
    }
}
