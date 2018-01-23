package com.sx.easytalk.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.easytalk.R;
import com.sx.easytalk.presenter.ContactPresenter;
import com.sx.easytalk.presenter.impl.ContactPresenterImpl;
import com.sx.easytalk.view.ContactView;
import com.sx.easytalk.widget.ContactLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends BaseFragment implements ContactView{

    private ContactPresenter mContactPresenter;
    private ContactLayout mContactLayout;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContactPresenter = new ContactPresenterImpl(this);
        mContactLayout = (ContactLayout) view.findViewById(R.id.contactLayout);
        /**
         * 初始化联系人
         */
        mContactPresenter.initContacts();
    }
}
