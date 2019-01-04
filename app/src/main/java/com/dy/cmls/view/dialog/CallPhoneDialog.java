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

public class CallPhoneDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;
    private String phone = "";

    public CallPhoneDialog(Context context, String phone, TJCallBack tjCallBack) {
        this.context = context;
        this.phone = phone;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_callphone, null);
        TextView tvTtiel1;
        TextView tvOk;
        TextView tvCancel;
        tvTtiel1 = (TextView) dialogView.findViewById(R.id.tvTtiel1);
        tvOk = (TextView) dialogView.findViewById(R.id.tvOk);
        tvCancel = (TextView) dialogView.findViewById(R.id.tvCancel);

        tvTtiel1.setText(phone);

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "callPhone");
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
