package com.sx.easytalk.utils;

import com.sx.easytalk.view.fragment.BaseFragment;
import com.sx.easytalk.view.fragment.ContactFragment;
import com.sx.easytalk.view.fragment.ConversationFragment;
import com.sx.easytalk.view.fragment.PluginFragment;

/**
 * @Author sunxin
 * @Date 2018/1/5 20:04
 * @Description Fragment的工厂类
 */

public class FragmentFactory {

    private static ConversationFragment sConversationFragment;
    private static ContactFragment sContactFragment;
    private static PluginFragment sPluginFragment;

    public static BaseFragment getFragment(int position) {
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                if (sConversationFragment == null) {
                    sConversationFragment = new ConversationFragment();
                }
                fragment = sConversationFragment;
                break;
            case 1:
                if (sContactFragment == null) {
                    sContactFragment = new ContactFragment();
                }
                fragment = sContactFragment;
                break;
            case 2:
                if (sPluginFragment == null) {
                    sPluginFragment = new PluginFragment();
                }
                fragment = sPluginFragment;
                break;
        }
        return fragment;
    }
}
