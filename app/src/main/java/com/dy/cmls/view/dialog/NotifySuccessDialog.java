package com.dy.cmls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.view.interfaces.TJCallBack;

public class NotifySuccessDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;

    public NotifySuccessDialog(Context context, TJCallBack tjCallBack) {
        this.context = context;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView =
            LayoutInflater.from(context).inflate(R.layout.dialog_notify_image_pay_srccess, null);
        ImageView ivImage;
        TextView tvTtiel2;
        TextView tvOk;
        ivImage = (ImageView) dialogView.findViewById(R.id.ivImage);
        tvTtiel2 = (TextView) dialogView.findViewById(R.id.tvTtiel2);
        tvOk = (TextView) dialogView.findViewById(R.id.tvOk);

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
}
