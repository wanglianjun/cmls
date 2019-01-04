package com.dy.cmls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dy.cmls.R;

public class ShowImageDialog {
    private Dialog dialog;
    private Context context;
    private int iamage;

    public ShowImageDialog(Context context, int iamage) {
        this.context = context;
        this.iamage = iamage;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_show_image, null);
        ImageView ivImage;
        RelativeLayout rlContent;
        rlContent = (RelativeLayout) dialogView.findViewById(R.id.rlContent);
        ivImage = (ImageView) dialogView.findViewById(R.id.ivImage);

        ivImage.setImageResource(iamage);

        rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ivImage.setOnClickListener(new View.OnClickListener() {
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
