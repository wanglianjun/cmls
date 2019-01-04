package com.dy.cmls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.view.interfaces.TJCallBack;

public class HuanKuanShouQuanDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;
    private String phone;
    private TextView tvGetCode;
    private EditText etCode;

    public HuanKuanShouQuanDialog(Context context, String phone, TJCallBack tjCallBack) {
        this.context = context;
        this.phone = phone;
        this.tjCallBack = tjCallBack;
    }

    public void show() {
        if (dialog != null) {
            etCode.setText(null);
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_credit_shouquan, null);
        TextView tvPhone;
        TextView tvClose;
        TextView tvConfirm;
        tvPhone = (TextView) dialogView.findViewById(R.id.tvPhone);
        etCode = (EditText) dialogView.findViewById(R.id.etCode);
        tvGetCode = (TextView) dialogView.findViewById(R.id.tvGetCode);
        tvClose = (TextView) dialogView.findViewById(R.id.tvClose);
        tvConfirm = (TextView) dialogView.findViewById(R.id.tvConfirm);

        tvPhone.setText(phone);

        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "getCode");
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
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = etCode.getText().toString();
                if (code == null || code.isEmpty()) {
                    if (tjCallBack != null) {
                        Intent intent = new Intent();
                        intent.putExtra("callBack", "null");
                        tjCallBack.callBack(intent);
                        return;
                    }
                }
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("code", code);
                    intent.putExtra("callBack", "confirm");
                    tjCallBack.callBack(intent);
                }
            }
        });

        dialog = new Dialog(context, R.style.dialog_center);
        dialog.setCancelable(true);
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(false);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                timer.cancel();
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "dismiss");
                    tjCallBack.callBack(intent);
                }
            }
        });
        dialog.show();
    }

    public void disDialog() {
        dialog.dismiss();
    }

    public void startTimer() {
        if (timer == null) {
            return;
        }
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            tvGetCode.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            timer.cancel();
            tvGetCode.setText("重新获取");
            tvGetCode.setClickable(true);
        }
    };
}
