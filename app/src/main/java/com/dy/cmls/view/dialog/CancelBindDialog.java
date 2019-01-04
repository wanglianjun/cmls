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
import com.dy.cmls.view.interfaces.TJCallBack;

public class CancelBindDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;
    private String text;

    public CancelBindDialog(Context context, String text, TJCallBack tjCallBack) {
        this.context = context;
        this.text = text;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_cancel_bind, null);
        TextView tvCancelBind;
        TextView tvClose;
        LinearLayout llContent;
        tvCancelBind = (TextView) dialogView.findViewById(R.id.tvCancelBind);
        tvClose = (TextView) dialogView.findViewById(R.id.tvClose);
        llContent = (LinearLayout) dialogView.findViewById(R.id.llContent);

        tvCancelBind.setText(text);

        llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
            }
        });
        tvCancelBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "cancelBind");
                    tjCallBack.callBack(intent);
                }
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
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

            }
        });
        dialog.show();
    }
}
