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

public class LdHuanKuanPlanDialog {
    private Dialog dialog;
    private Context context;
    private Intent intent;
    private TJCallBack tjCallBack;

    public LdHuanKuanPlanDialog(Context context, Intent intent, TJCallBack tjCallBack) {
        this.context = context;
        this.intent = intent;
        this.tjCallBack = tjCallBack;
    }

    public boolean isShowing() {
        return dialog != null && dialog.isShowing() ? true : false;
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void updateDialog(Intent intent) {

    }

    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
            return;
        }
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_ld_huankuan_plan, null);
        TextView tvMoney1;
        TextView tvMoney2;
        TextView tvMoney3;
        TextView tvMoney4;
        TextView tvClose;
        TextView tvOk;
        tvMoney1 = (TextView) dialogView.findViewById(R.id.tvMoney1);
        tvMoney2 = (TextView) dialogView.findViewById(R.id.tvMoney2);
        tvMoney3 = (TextView) dialogView.findViewById(R.id.tvMoney3);
        tvMoney4 = (TextView) dialogView.findViewById(R.id.tvMoney4);
        tvClose = (TextView) dialogView.findViewById(R.id.tvClose);
        tvOk = (TextView) dialogView.findViewById(R.id.tvOk);

        String money1 = intent.getStringExtra("money1");
        String money2 = intent.getStringExtra("money2");
        String money3 = intent.getStringExtra("money3");
        String money4 = intent.getStringExtra("money4");

        tvMoney1.setText("¥" + money1);
        tvMoney2.setText("¥" + money2);
        tvMoney3.setText("¥" + money3);
        tvMoney4.setText("¥" + money4);

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.putExtra("callBack", "confirm");
                tjCallBack.callBack(intent);
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
