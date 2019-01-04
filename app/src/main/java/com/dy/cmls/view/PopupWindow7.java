package com.dy.cmls.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class PopupWindow7 extends PopupWindow {
    public PopupWindow7(Context context) {
        super(context);
    }

    public PopupWindow7(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PopupWindow7(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public PopupWindow7(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public PopupWindow7(View contentView) {
        super(contentView);
    }

    public PopupWindow7() {
        super();
    }

    public PopupWindow7(int width, int height) {
        super(width, height);
    }

    public PopupWindow7(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    public PopupWindow7(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }

    public void show(View anchor){
        super.showAsDropDown(anchor);
    }

}
