package com.dy.cmls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.dy.cmls.R;
import com.dy.cmls.view.interfaces.TJCallBack;

/**
 * Created by lcjing on 2018/11/29.
 */

public class BindDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;
    private String msg,ok;

    public BindDialog(Context context, TJCallBack tjCallBack) {
        this.context = context;
        this.tjCallBack = tjCallBack;
    }

    public BindDialog(TJCallBack tjCallBack, Context context, String msg, String ok) {
        this.tjCallBack = tjCallBack;
        this.context = context;
        this.msg = msg;
        this.ok = ok;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_bind, null);
        TextView tvOk,tvTitle;
        TextView tvCancel;
        tvOk =  dialogView.findViewById(R.id.tvOk);
        tvTitle =  dialogView.findViewById(R.id.tvTtiel1);
        tvCancel =  dialogView.findViewById(R.id.tvCancel);

        if (!StringUtils.isEmpty(msg)) {
            tvTitle.setText(msg);
        }

        if (!StringUtils.isEmpty(ok)) {
            tvOk.setText(ok);
        }
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "bind");
                    tjCallBack.callBack(intent);
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog = new Dialog(context, R.style.dialog_center);
        dialog.setCancelable(true);
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(true);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        dialog.show();
    }
}

