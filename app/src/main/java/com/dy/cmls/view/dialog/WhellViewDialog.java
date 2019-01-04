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

import java.util.List;

public class WhellViewDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;
    private List<String> list;
    private String title = "";
    private int index = 0;

    public WhellViewDialog(Context context, String title, List<String> list, TJCallBack tjCallBack) {
        this.context = context;
        this.title = title;
        this.list = list;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_wheelview, null);
        LinearLayout llContent;
        TextView tvTitle;
        TextView tvConfirm;
        WheelView wheelView;
        llContent = (LinearLayout) dialogView.findViewById(R.id.llContent);
        tvTitle = (TextView) dialogView.findViewById(R.id.tvTitle);
        tvConfirm = (TextView) dialogView.findViewById(R.id.tvConfirm);
        wheelView = (WheelView) dialogView.findViewById(R.id.wheelView);

        tvTitle.setText(title);
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

        wheelView.setOffset(2);
        wheelView.setItems(list);
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                index = selectedIndex - 2;
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
                    intent.putExtra("callBack", index);
                    tjCallBack.callBack(intent);
                }
            }
        });
        dialog.show();
    }
}
