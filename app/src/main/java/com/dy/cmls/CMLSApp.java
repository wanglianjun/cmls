package com.dy.cmls;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

//import com.dy.cmls.mlogin.bean.UserDataBean;

/**
 * Created by lj on 2017/7/25.
 * 应用配置信息
 */

public class CMLSApp extends Application {
    //打开的activity
    private static CopyOnWriteArrayList<Activity> activities = new CopyOnWriteArrayList<Activity>();

//    public static UserDataBean.InfoBean userData;
    public static String za_userid;
    //卡福

    private static CMLSApp instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.getCacheDir();
        instance = this;
        //bugly
        Utils.init(this);
        CrashReport.initCrashReport(getApplicationContext(), "31ce403936", false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //7.0
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
    }

    //通过重写以下两个方法, 使APP监听到内存不足时通知Glide清除缓存, 最直接防止图片OOM的方法
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

    public static CMLSApp getInstance() {
        return instance;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 退回MainActivity
     */
    public static void toMainActivity() {
        for (Activity act : activities) {
//            if (act instanceof KFMainActivity) {
//
//            } else if (act instanceof KFLoginActivity) {
//
//            } else
                {
                finishActivity(act);
            }
        }
    }

    /**
     * 新建了一个activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }



    /**
     * 应用退出，结束所有的activity
     */
    public static void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }

    /**
     * 关闭Activity列表中的所有Activity
     */
    public void finishActivity() {
        for (Activity activity : activities) {
            if (null != activity) {
                activity.finish();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
