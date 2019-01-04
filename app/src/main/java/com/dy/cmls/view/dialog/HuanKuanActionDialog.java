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

public class HuanKuanActionDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;

    public HuanKuanActionDialog(Context context, TJCallBack tjCallBack) {
        this.context = context;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_huankuan_action, null);
        TextView tvAddCard1;
        TextView tvAddCard2;
        LinearLayout llContent;
        tvAddCard1 = (TextView) dialogView.findViewById(R.id.tvAddCard1);
        tvAddCard2 = (TextView) dialogView.findViewById(R.id.tvAddCard2);
        llContent = (LinearLayout) dialogView.findViewById(R.id.llContent);

        llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvAddCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "1");
                    tjCallBack.callBack(intent);
                }
            }
        });
        tvAddCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "2");
                    tjCallBack.callBack(intent);
                }
            }
        });

        dialog = new Dialog(context, R.style.dialog_right);
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
