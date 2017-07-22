package com.sx.easytalk.view;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sx.easytalk.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_username;
    private TextInputLayout til_username;
    private EditText et_pwd;
    private TextInputLayout til_pwd;
    private Button btn_login;
    private TextView new_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        til_username = (TextInputLayout) findViewById(R.id.til_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        til_pwd = (TextInputLayout) findViewById(R.id.til_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        new_user = (TextView) findViewById(R.id.new_user);

        btn_login.setOnClickListener(this);
        new_user.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                break;
            case R.id.new_user:
                startActivity(RegistActivity.class,false);
                break;
        }
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

        // TODO validate success, do something


    }
}
