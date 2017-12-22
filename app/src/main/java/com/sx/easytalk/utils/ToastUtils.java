package com.sx.easytalk.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @Author sunxin
 * @Date 2017/12/22 21:31
 * @Description 单例土司工具类
 */

public class ToastUtils {

    private static Toast sToast;

    public static void showToast(Context context, String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
        sToast.setText(msg);
        sToast.show();
    }
}
