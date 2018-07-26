package com.example.wang.livedetectionapp.common;

import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class AppManager {

    private static List<BaseActivity> mActivities = new ArrayList<>();

    /**
     * 添加Activity到列表
     */
    public static void addActivity(BaseActivity baseActivity) {
        mActivities.add(baseActivity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public static BaseActivity currentActivity() {
        return mActivities.get(countActivity() - 1);
    }

    /**
     * 结束当前Activity（列表中最后一个压入的）
     */
    public static void finishCurrentActivity() {
        BaseActivity activity = mActivities.remove(countActivity() - 1);
        activity.finish();
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(BaseActivity activity) {
        if (activity != null) {
            mActivities.remove(activity);
            if(!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        for (BaseActivity activity : mActivities) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        for (BaseActivity activity : mActivities) {
            if (activity != null) {
                activity.finish();
            }
        }
        mActivities.clear();
    }

    /**
     * 得到队列中的总数
     */
    public static int countActivity() {
        return mActivities.size();
    }

    /**
     * 退出应用程序
     */
    public static void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager manager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            manager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}