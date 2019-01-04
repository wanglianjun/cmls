package com.dy.cmls.utils;

import android.util.Log;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class LogUtil {
    public static boolean isShowLog = true;

    public static void v(String title, String msg) {
        if (isShowLog) {
            Log.v("TJ", msg);
        }
    }

    public static void d(String title, String msg) {
        if (isShowLog) {
            Log.d("TJ", msg);
        }
    }

    public static void i(String title, String msg) {
        if (isShowLog) {
            Log.i("TJ", msg);
        }
    }

    public static void w(String title, String msg) {
        if (isShowLog) {
            Log.w("TJ", msg);
        }
    }

    public static void e(String title, String msg) {
        if (isShowLog) {
            Log.e("TJ", title + msg);
        }
    }
}

