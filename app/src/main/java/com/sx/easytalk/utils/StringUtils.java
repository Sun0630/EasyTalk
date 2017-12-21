package com.sx.easytalk.utils;

import android.text.TextUtils;

/**
 * @Author sunxin
 * @Date 2017/12/21 22:11
 * @Description
 */

public class StringUtils {
    /**
     * 校验用户名，开头必须是字母，然后可以是任意字符，2-19位
     *
     * @param userName
     * @return
     */
    public static boolean checkUserName(String userName) {
        if (TextUtils.isEmpty(userName)) {
            return false;
        }
        return userName.matches("^[a-z]\\w{2,19}$");
    }

    /**
     * 校验密码  3到20位数字
     *
     * @param pwd
     * @return
     */
    public static boolean checkPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            return false;
        }
        return pwd.matches("^\\d{3,20}$");
    }
}
