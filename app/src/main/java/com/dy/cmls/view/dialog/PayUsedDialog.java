package com.dy.cmls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.view.interfaces.TJCallBack;

public class PayUsedDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;

    public PayUsedDialog(Context context, TJCallBack tjCallBack) {
        this.context = context;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_pay_used, null);
        TextView tvPay1;
        TextView tvPay2;
        TextView tvCancel;
        tvPay1 = (TextView) dialogView.findViewById(R.id.tvPay1);
        tvPay2 = (TextView) dialogView.findViewById(R.id.tvPay2);
        tvCancel = (TextView) dialogView.findViewById(R.id.tvCancel);

        tvPay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "pay1");
                    tjCallBack.callBack(intent);
                }
            }
        });
        tvPay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "pay2");
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
