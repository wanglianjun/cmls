package com.dy.cmls.utils;

import android.content.Context;

/**
 * Created by lcjing on 2018/12/24.
 */

public class CommonUtil {
    /**
     * 根据手机分辨率从dp转成px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
