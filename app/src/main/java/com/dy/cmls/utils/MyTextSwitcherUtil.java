package com.dy.cmls.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.dy.cmls.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/5.
 */

public class MyTextSwitcherUtil {
    private TextSwitcher mTextSwitcher;
    private Context mContext;
    private List<String> mWarningTextList ;

    public MyTextSwitcherUtil(TextSwitcher mTextSwitcher, Context mContext, List<String> mWarningTextList) {
        this.mTextSwitcher = mTextSwitcher;
        this.mContext = mContext;
        this.mWarningTextList = mWarningTextList;
        setTextSwitcher();
    }

    public TextSwitcher getmTextSwitcher() {
        return mTextSwitcher;
    }

    public void setmTextSwitcher(TextSwitcher mTextSwitcher) {
        this.mTextSwitcher = mTextSwitcher;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<String> getmWarningTextList() {
        return mWarningTextList;
    }

    public void setmWarningTextList(List<String> mWarningTextList) {
        this.mWarningTextList = mWarningTextList;
    }

    private int index = 0;//textview上下滚动下标
    private Handler handler = new Handler();
    private boolean isFlipping = false; // 是否启用预警信息轮播

    public void setTextSwitcher() {
        mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));
        mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top));
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(mContext);
                textView.setSingleLine();
                textView.setTextSize(12);//字号
                textView.setTextColor(Color.parseColor("#ff999999"));
                textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                textView.setSingleLine();
                textView.setGravity(Gravity.CENTER);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.gravity = Gravity.CENTER;
                textView.setLayoutParams(params);
                textView.setPadding(25, 0, 25, 0);
                return textView;
            }
        });
        setData();
    }

    public String getText(){
        return mWarningTextList.get(index % mWarningTextList.size());
    }

    public int getIndex(){
        return (index % mWarningTextList.size());
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isFlipping) {
                return;
            }
            index++;
            mTextSwitcher.setText(mWarningTextList.get(index % mWarningTextList.size()));
            if (index == mWarningTextList.size()) {
                index = 0;
            }
            startFlipping();
        }
    };

    //开启信息轮播
    public void startFlipping() {
        if (mWarningTextList.size() > 1) {
            handler.removeCallbacks(runnable);
            isFlipping = true;
            handler.postDelayed(runnable, 3000);
        }
    }

    //关闭信息轮播
    public void stopFlipping() {
        if (mWarningTextList.size() > 1) {
            isFlipping = false;
            handler.removeCallbacks(runnable);
        }
    }

    //设置数据
    private void setData() {
        if (mWarningTextList.size() == 1) {
            mTextSwitcher.setText(mWarningTextList.get(0));
            index = 0;
        }
        if (mWarningTextList.size() > 1) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mTextSwitcher.setText(mWarningTextList.get(0));
                    index = 0;
                }
            }, 1000);
            mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));
            mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top));
            startFlipping();
        }
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        startFlipping();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        stopFlipping();
//    }
}
