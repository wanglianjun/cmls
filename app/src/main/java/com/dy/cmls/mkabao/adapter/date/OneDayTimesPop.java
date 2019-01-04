package com.dy.cmls.mkabao.adapter.date;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.ConsumTypeBean;
import com.dy.cmls.view.WheelView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/7.
 */

public class OneDayTimesPop {
    private int count = 2;
    private TextView tvDayTimes;

    //每日分期次数
    private List<ConsumTypeBean> timesPdayList = new ArrayList<>();
    private String timesPday, tempTimesPday;


    public OneDayTimesPop(int count, TextView tvDayTimes) {
        this.count = count;
        this.tvDayTimes = tvDayTimes;
        initList();
    }

    public void setCount(int count) {
        this.count = count;
        initList();
    }

    private void initList() {
        timesPdayList.clear();
        for (int i = 1; i <= count; i++) {
            timesPdayList.add(new ConsumTypeBean(i, "" + i));
        }
    }

    public void show() {
        initPopupTimePDay(timesPdayList);
    }

    //每日分期次数
    private void initPopupTimePDay(final List<ConsumTypeBean> listBean) {
        tempTimesPday = listBean.get(0).getName();
        timesPday = tempTimesPday;
        tvDayTimes.setText(timesPday);
        List<String> list = new ArrayList<>();
        for (ConsumTypeBean bean : listBean) {
            list.add(bean.getName());
        }
        final View popView = View.inflate(tvDayTimes.getContext(), R.layout.popup_select, null);
        final PopupWindow timesPDayPopupWindow =
                new PopupWindow(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
        WheelView wheelView;
        ImageView ivClose;
        TextView tvConfirm;
        wheelView =  popView.findViewById(R.id.wheelView);
        ivClose =  popView.findViewById(R.id.ivClose);
        tvConfirm =  popView.findViewById(R.id.tvConfirm);

        wheelView.setOffset(2);
        wheelView.setItems(list);
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                tempTimesPday = item;
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timesPDayPopupWindow.dismiss();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timesPday = tempTimesPday;
                tvDayTimes.setText(timesPday);
                timesPDayPopupWindow.dismiss();
            }
        });
        timesPDayPopupWindow.setFocusable(true);
        timesPDayPopupWindow.setContentView(popView);
        timesPDayPopupWindow.setAnimationStyle(R.style.popwin_anim_style1);
        timesPDayPopupWindow.showAtLocation(tvConfirm, Gravity.BOTTOM, 0, 0);
    }
}
