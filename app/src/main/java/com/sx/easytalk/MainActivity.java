package com.sx.easytalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.sx.easytalk.utils.FragmentFactory;
import com.sx.easytalk.view.BaseActivity;
import com.sx.easytalk.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private int[] titleIds = {R.string.conversation,R.string.contact,R.string.plugin};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initBottomNavigation();
        initFragment();
    }


    private void initView() {
        ButterKnife.bind(this);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTvTitle.setText(titleIds[0]);
    }

    private void initBottomNavigation() {
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.conversation_selected_2, titleIds[0]))
                .addItem(new BottomNavigationItem(R.mipmap.contact_selected_2, titleIds[1]))
                .addItem(new BottomNavigationItem(R.mipmap.plugin_selected_2, titleIds[2]))
                .setActiveColor(R.color.btnNormal)
                .setInActiveColor(R.color.inActive)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
    }


    private void initFragment() {
        /*解决热部署Fragment重影问题*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < titleIds.length; i++) {
            Fragment fragment = fragmentManager.findFragmentByTag(i + "");
            if (fragment != null) {
                transaction.remove(fragment);
            }
        }

        transaction.commit();

        getSupportFragmentManager().beginTransaction().add(R.id.fl_container,
                FragmentFactory.getFragment(0), "0").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuBuilder builder = (MenuBuilder) menu;
        builder.setOptionalIconsVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_friedn:
                showToast("添加好友");
                // TODO: 2017/12/25 跳转添加好友
                break;
            case R.id.add_scan:
                showToast("扫一扫");
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


    @Override
    public void onTabSelected(int position) {
        BaseFragment fragment = FragmentFactory.getFragment(position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_container, FragmentFactory.getFragment(position));
        }
        transaction.show(fragment).commit();
        mTvTitle.setText(titleIds[position]);
    }

    @Override
    public void onTabUnselected(int position) {
        getSupportFragmentManager()
                .beginTransaction()
                .hide(FragmentFactory.getFragment(position))
                .commit();
    }

    @Override
    public void onTabReselected(int position) {

    }
}
