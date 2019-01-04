package com.dy.cmls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dy.cmls.R;
import com.dy.cmls.view.interfaces.TJCallBack;

public class PayUsedCodeDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;

    public PayUsedCodeDialog(Context context, TJCallBack tjCallBack) {
        this.context = context;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_pay_used_code, null);
        final EditText etCode;
        ImageView ivScan;
        TextView tvOk;
        TextView tvCancel;
        etCode = (EditText) dialogView.findViewById(R.id.etCode);
        ivScan = (ImageView) dialogView.findViewById(R.id.ivScan);
        tvOk = (TextView) dialogView.findViewById(R.id.tvOk);
        tvCancel = (TextView) dialogView.findViewById(R.id.tvCancel);

        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "scan");
                    tjCallBack.callBack(intent);
                }
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = etCode.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    Toast.makeText(context, "请输入激活码", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.dismiss();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("code", code);
                    intent.putExtra("callBack", "code");
                    tjCallBack.callBack(intent);
                }
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
