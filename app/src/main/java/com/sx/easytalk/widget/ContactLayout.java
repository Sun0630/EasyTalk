package com.sx.easytalk.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sx.easytalk.R;

/**
 * @Author sunxin
 * @Date 2018/1/6 17:19
 * @Description 自定义组合控件 联系人
 */

public class ContactLayout extends RelativeLayout {

    private RecyclerView mRecyclerView;
    private TextView mTextView;
    private SlideBar mSlideBar;

    public ContactLayout(Context context) {
        this(context,null);
    }

    public ContactLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.contact_layout,this,true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mTextView = (TextView) findViewById(R.id.tv_letter);
        mSlideBar = (SlideBar) findViewById(R.id.slideBar);

    }

    public ContactLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }
}
