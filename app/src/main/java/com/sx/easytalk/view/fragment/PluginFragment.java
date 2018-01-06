package com.sx.easytalk.view.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hyphenate.chat.EMClient;
import com.sx.easytalk.MainActivity;
import com.sx.easytalk.R;
import com.sx.easytalk.presenter.LogoutPresenter;
import com.sx.easytalk.presenter.impl.LogoutPresenterImpl;
import com.sx.easytalk.utils.ToastUtils;
import com.sx.easytalk.view.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PluginFragment extends BaseFragment implements View.OnClickListener, PluginView {


    private Button mLogout;
    private LogoutPresenter mLogoutPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLogoutPresenter = new LogoutPresenterImpl(this);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        return inflater.inflate(R.layout.fragment_plugin, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLogout = (Button) view.findViewById(R.id.btn_logout);
        mLogout.setText("（" + EMClient.getInstance().getCurrentUser() + "）退出登陆");
        mLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mProgressDialog.setMessage("正在退出...");
        mProgressDialog.show();
        mLogoutPresenter.logout();
    }

    @Override
    public void onLogout(String userName, boolean isSuccess, String errorMsg) {
        mProgressDialog.dismiss();
        if (isSuccess){
            //退出成功，回到登陆页面
            MainActivity activity = (MainActivity) getActivity();
            activity.startActivity(LoginActivity.class,true);
        }else {
            ToastUtils.showToast(getActivity(),errorMsg);
        }
    }
}
