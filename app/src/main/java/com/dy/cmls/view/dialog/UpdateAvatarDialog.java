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

public class UpdateAvatarDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;

    public UpdateAvatarDialog(Context context, TJCallBack tjCallBack) {
        this.context = context;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_update_avatar, null);
        TextView tvPaiZhao;
        TextView tvXiangCe;
        TextView tvYuanTu;
        TextView tvClose;
        LinearLayout llContent;
        tvPaiZhao = (TextView) dialogView.findViewById(R.id.tvPaiZhao);
        tvXiangCe = (TextView) dialogView.findViewById(R.id.tvXiangCe);
        tvYuanTu = (TextView) dialogView.findViewById(R.id.tvYuanTu);
        tvClose = (TextView) dialogView.findViewById(R.id.tvClose);
        llContent = (LinearLayout) dialogView.findViewById(R.id.llContent);

        llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
            }
        });
        tvPaiZhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "paiZhao");
                    tjCallBack.callBack(intent);
                }
            }
        });
        tvXiangCe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "xiangCe");
                    tjCallBack.callBack(intent);
                }
            }
        });
        tvYuanTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "yuanTu");
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
