package com.dy.cmls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.view.interfaces.TJCallBack;

public class NotifyImageDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;
    private int image;
    private String message = "";
    private String textOk = "";

    public NotifyImageDialog(Context context, int image, String message, String textOk, TJCallBack tjCallBack) {
        this.context = context;
        this.image = image;
        this.message = message;
        this.textOk = textOk;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_notify_image, null);
        ImageView ivImage;
        TextView tvTtiel2;
        TextView tvOk;
        ivImage = (ImageView) dialogView.findViewById(R.id.ivImage);
        tvTtiel2 = (TextView) dialogView.findViewById(R.id.tvTtiel2);
        tvOk = (TextView) dialogView.findViewById(R.id.tvOk);

        ivImage.setImageResource(image);
        tvTtiel2.setText(message);
        tvOk.setText(textOk);

        tvOk.setTextColor(ContextCompat.getColor(context, R.color.app_main));

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
        dialog.setCanceledOnTouchOutside(true);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
//                if (tjCallBack != null) {
//                    Intent intent = new Intent();
//                    intent.putExtra("callBack", "ok");
//                    tjCallBack.callBack(intent);
//                }
            }
        });
        dialog.show();
    }
}
