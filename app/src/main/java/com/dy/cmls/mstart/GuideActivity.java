package com.dy.cmls.mstart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mstart.adapter.GuideBannerLocationAdapter;
import com.dy.cmls.view.interfaces.TJCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GuideActivity extends BaseActivity {
    @BindView(R.id.guideVp) ViewPager guideVp;
    @BindView(R.id.guideRg) RadioGroup guideRg;
    @BindView(R.id.guideTvTime) TextView guideTvTime;
    private boolean isStartTime;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initView();
    }

    @Override protected void initView() {
        showLocation();
        guideRg.setVisibility(View.GONE);
    }



    //加载本地图片的情况
    private void showLocation() {
        final int[] list = { R.mipmap.yin1, R.mipmap.yin2,R.mipmap.yin3};
        for (int i = 0; i < list.length; i++) {
            LayoutInflater.from(this).inflate(R.layout.layout_guide_rg_bg, guideRg);
        }
        guideRg.check(guideRg.getChildAt(0).getId());
        guideVp.setAdapter(new GuideBannerLocationAdapter(this, list, new TJCallBack() {
            @Override public void callBack(Intent intent) {
                if (intent == null) {
                    return;
                }
                int position = intent.getIntExtra("position", -1);
                if (position == -1) {
                    return;
                }
                if (position == list.length - 1) {
                    jumpToPage(MainActivity.class);
                    finish();
                }
            }
        }));
        guideVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override public void onPageSelected(int position) {
                guideRg.check(guideRg.getChildAt(position).getId());
                if (position == list.length - 1 && !isStartTime) {
                    isStartTime = true;
                    final int[] number = { 3 };
                    guideTvTime.setVisibility(View.VISIBLE);
                    guideTvTime.setOnClickListener(new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            number[0] = -1;
                            jumpToPage(MainActivity.class);
                            finish();
                        }
                    });
                    new Thread() {
                        @Override public void run() {
                            while (number[0] >= 0) {
                                try {
                                    if (number[0] == 0) {
                                        Message msg = Message.obtain();
                                        msg.what = 1;
                                        msg.obj = number[0];
                                        handler.sendMessage(msg);
                                        return;
                                    }
                                    Message msg = Message.obtain();
                                    msg.what = 1;
                                    msg.obj = number[0];
                                    handler.sendMessage(msg);
                                    number[0]--;
                                    sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                }
            }

            @Override public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private Handler handler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int number = (int) msg.obj;
                    if (number != 0) {
                        guideTvTime.setText(String.valueOf(number) + "s 跳过");
                    } else {
                        jumpToPage(MainActivity.class);
                        finish();
                    }
                    break;
            }
        }
    };
}
