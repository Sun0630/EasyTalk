package com.sx.easytalk.view;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sx.easytalk.R;
import com.sx.easytalk.presenter.RegistPresenter;
import com.sx.easytalk.presenter.RegistePresenterImpl;
import com.sx.easytalk.utils.StringUtils;

public class RegistActivity extends BaseActivity implements View.OnClickListener, TextView.OnEditorActionListener, RegistView {

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
        StatusBarUtil.setTransparent(this);
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
        mRegistPresenter.regist(username, pwd);
    }

    private void submit() {
        // validate
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
        showDialog("正在注册中...");
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

    /**
     * 注册回调
     *
     * @param userName
     * @param pwd
     * @param isSuccess 是否成功
     * @param errorMsg  错误信息
     */
    @Override
    public void onRegist(String userName, String pwd, boolean isSuccess, String errorMsg) {
        hideDialog();
        if (isSuccess) {
            //注册成功,把信息保存到本地，跳转到登陆页面
            saveUser(userName,pwd);
            startActivity(LoginActivity.class,true);
        } else {
            showToast("注册失败" + errorMsg);
        }
    }
}
