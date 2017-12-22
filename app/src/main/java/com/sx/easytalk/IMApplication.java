package com.sx.easytalk;

import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.Iterator;
import java.util.List;

import cn.bmob.v3.Bmob;

/**
 * @Author sunxin
 * @Date 2017/7/22 16:53
 * @Description
 */

public class IMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化环信
        initEaseMob();
        //初始化Bmob
        initBmob();
        //初始化Logger
        initLogger();
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 初始化Bmob
     */
    private void initBmob() {
        Bmob.initialize(this,"9cafeb8852d63494346f013f18a0872e");
    }

    /**
     * 初始化环信
     */
    private void initEaseMob() {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);

        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(getPackageName())) {
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

        //初始化
        EMClient.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }


    /**
     * 获取应用名称
     * @param pID
     * @return
     */
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

}
