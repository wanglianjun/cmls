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

public class ErrorDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;
    private String title1 = "";
    private String title2 = "";
    private String textOk = "";
    private String textClose = "";

    public ErrorDialog(Context context, String title1, String title2, String textOk, String textClose,
                       TJCallBack tjCallBack) {
        this.context = context;
        this.title1 = title1;
        this.title2 = title2;
        this.textOk = textOk;
        this.textClose = textClose;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_error_login, null);
        TextView tvTtiel1;
        TextView tvTtiel2;
        TextView tvOk;
        TextView tvCancel;
        tvTtiel1 = (TextView) dialogView.findViewById(R.id.tvTtiel1);
        tvTtiel2 = (TextView) dialogView.findViewById(R.id.tvTtiel2);
        tvOk = (TextView) dialogView.findViewById(R.id.tvOk);
        tvCancel = (TextView) dialogView.findViewById(R.id.tvCancel);

        tvTtiel1.setText(title1);
        tvTtiel2.setText(title2);
        tvOk.setText(textOk);
        tvCancel.setText(textClose);

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "ok");
                    tjCallBack.callBack(intent);
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "close");
                    tjCallBack.callBack(intent);
                }
            }
        });

        dialog = new Dialog(context, R.style.dialog_center);
        dialog.setCancelable(false);
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(false);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        dialog.show();
    }

    public Dialog getDialog() {
        if (dialog != null) {
            return dialog;
        } else {
            return null;
        }
    }
}
