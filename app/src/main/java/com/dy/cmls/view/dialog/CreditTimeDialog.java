package com.dy.cmls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.view.WheelView;
import com.dy.cmls.view.interfaces.TJCallBack;

import java.util.ArrayList;
import java.util.List;

public class CreditTimeDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;
    private String youxiaoqiMonth = "01";
    private String youxiaoqiYear = "18";

    public CreditTimeDialog(Context context, TJCallBack tjCallBack) {
        this.context = context;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_credit_time, null);
        LinearLayout llContent;
        TextView tvTitle;
        TextView tvConfirm;
        WheelView wheelViewMonth;
        WheelView wheelViewYear;
        llContent = (LinearLayout) dialogView.findViewById(R.id.llContent);
        tvTitle = (TextView) dialogView.findViewById(R.id.tvTitle);
        tvConfirm = (TextView) dialogView.findViewById(R.id.tvConfirm);
        wheelViewMonth = (WheelView) dialogView.findViewById(R.id.wheelViewMonth);
        wheelViewYear = (WheelView) dialogView.findViewById(R.id.wheelViewYear);

        tvTitle.setText("选择信用卡有效期");

        List<String> list = new ArrayList<>();
        list.add("01月");
        list.add("02月");
        list.add("03月");
        list.add("04月");
        list.add("05月");
        list.add("06月");
        list.add("07月");
        list.add("08月");
        list.add("09月");
        list.add("10月");
        list.add("11月");
        list.add("12月");
        wheelViewMonth.setOffset(2);
        wheelViewMonth.setItems(list);
        wheelViewMonth.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                youxiaoqiMonth = "";
                String[] strs = item.split("月");
                youxiaoqiMonth += strs[0];
            }
        });

        List<String> list2 = new ArrayList<>();
        list2.add("2018年");
        list2.add("2019年");
        list2.add("2020年");
        list2.add("2021年");
        list2.add("2022年");
        list2.add("2023年");
        list2.add("2024年");
        list2.add("2025年");
        list2.add("2026年");
        list2.add("2027年");
        list2.add("2028年");
        list2.add("2029年");
        list2.add("2030年");
        list2.add("2031年");
        list2.add("2032年");
        list2.add("2033年");
        list2.add("2034年");
        list2.add("2035年");
        list2.add("2036年");
        list2.add("2037年");
        list2.add("2038年");
        list2.add("2039年");
        list2.add("2040年");
        list2.add("2041年");
        list2.add("2042年");
        list2.add("2043年");
        list2.add("2044年");
        list2.add("2045年");
        list2.add("2046年");
        list2.add("2047年");
        list2.add("2048年");
        list2.add("2049年");
        list2.add("2050年");
        list2.add("2051年");
        list2.add("2052年");
        list2.add("2053年");
        list2.add("2054年");
        list2.add("2055年");
        list2.add("2056年");
        list2.add("2057年");
        list2.add("2058年");
        list2.add("2059年");
        list2.add("2060年");
        wheelViewYear.setOffset(2);
        wheelViewYear.setItems(list2);
        wheelViewYear.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                youxiaoqiYear = "";
                String[] strs = item.split("年");
                youxiaoqiYear += strs[0].substring(2);
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog = new Dialog(context, R.style.dialog_bottom);
        dialog.setCancelable(true);
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(true);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("youxiaoqiYear", youxiaoqiYear);
                    intent.putExtra("youxiaoqiMonth", youxiaoqiMonth);
                    tjCallBack.callBack(intent);
                }
            }
        });
        dialog.show();
    }
}
