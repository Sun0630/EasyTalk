package com.sx.easytalk.presenter.impl;

import com.hyphenate.chat.EMClient;
import com.sx.easytalk.db.DbUtils;
import com.sx.easytalk.presenter.ContactPresenter;
import com.sx.easytalk.view.ContactView;

import java.util.List;

/**
 * @Author sunxin
 * @Date 2018/1/23 21:21
 * @Description
 */

public class ContactPresenterImpl implements ContactPresenter {

    private ContactView mContactView;

    public ContactPresenterImpl(ContactView contactView) {
        mContactView = contactView;
    }

    @Override
    public void initContacts() {
        //1.先读取缓存中的
        String currentUser = EMClient.getInstance().getCurrentUser();
        List<String> contacts = DbUtils.getContacts(currentUser);
    }
}
