package com.sx.easytalk.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author sunxin
 * @Date 2017/12/21 22:51
 * @Description 封装一个单线程线程池
 */

public class ThreadUtils {
    private static Executor executor = Executors.newSingleThreadExecutor();
    //绑定主线程
    private static Handler handler = new Handler(Looper.getMainLooper());
    /**
     * 在子线程运行任务
     * @param runnable
     */
    public static void runOnSubThread(Runnable runnable) {
        executor.execute(runnable);
    }

    /**
     * 泡在主线程
     * @param runnable
     */
    public static void runOnMainThread(Runnable runnable){
        handler.post(runnable);
    }
}
